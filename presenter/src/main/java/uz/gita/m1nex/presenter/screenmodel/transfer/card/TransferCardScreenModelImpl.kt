package uz.gita.m1nex.presenter.screenmodel.transfer.card

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import uz.gita.m1nex.core.asFail
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.core.onFail
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.presenter.screenmodel.transfer.verify.TransferVerifyContract
import uz.gita.m1nex.usecase.card.CardUseCase
import uz.gita.m1nex.usecase.home.HomeUseCase
import uz.gita.m1nex.usecase.transfer.TransferUseCase
import javax.inject.Inject


internal class TransferCardScreenModelImpl @Inject constructor(
    private val direction: TransferCardContract.Direction,
    private val transferUseCase: TransferUseCase,
    private val homeUseCase: HomeUseCase,
    private val cardUseCase: CardUseCase
) : TransferCardContract.Model {
    init {
        cardUseCase.getCards().onEach {
            if (card == null) {
                it.onSuccess {
                    allCards = this
                    card = this.getOrElse(0){
                        CardData("-1","Paynet Card",0,"","",28,6,0,true)
                    }
                    delay(200L)
                    intent {
                        this@intent.reduce { TransferCardContract.UIState.CurrentCard(card ?: this@onSuccess.getOrElse(0){
                            CardData("-1","Paynet Card",0,"","",28,6,0,true)
                        }) }
                    }
                }
            }
        }.launchIn(screenModelScope)
    }
    
    private var card: CardData? = null
    private var allCards: List<CardData> = ArrayList()
    override fun onEventDispatcher(intent: TransferCardContract.Intent) = intent {
        when (intent) {
            TransferCardContract.Intent.Back -> {
                direction.back()
            }

            TransferCardContract.Intent.ToAddCardScreen -> {
                direction.navigateToAddCard()
            }

            is TransferCardContract.Intent.SelectCurrentCard -> {
                card = intent.it
                reduce { TransferCardContract.UIState.CurrentCard(card!!) }
            }

            TransferCardContract.Intent.GetCards -> {
                cardUseCase.getCards().onEach {
                    it.onSuccess {
                        allCards = this
                        this@intent.reduce { TransferCardContract.UIState.CurrentCard(card?: this@onSuccess.getOrElse(0){
                            CardData("-1","Paynet Card",0,"","",28,6,0,true)
                        }) }

                    }
                }.launchIn(screenModelScope)
            }

            is TransferCardContract.Intent.Pay -> {
                var pan = intent.receiverPan
                if (intent.pankey == 1) {
                    pan = "000800080008${intent.receiverPan}"
                }
                transferUseCase.transfer(receiverPan = pan,type = if (allCards.any { it.pan == intent.receiverPan })"own-card" else "third-card",amount = intent.amount.toLong(), senderId = card!!.id).onEach {
                    it.onSuccess {
                        homeUseCase.getPhone().onEach {
                            it.onSuccess {
                                direction.navigateToVerify(this)
                            }
                        }.launchIn(screenModelScope)
                    }
                }.launchIn(scope = screenModelScope)
            }

            is TransferCardContract.Intent.ClickAllCardsDialog -> {
                cardUseCase.getCards().onEach {
                    it.onSuccess {
                        allCards = this
                    }
                }.launchIn(screenModelScope)
                postSideEffect(TransferCardContract.SideEffect.ShowAllCardsDialog(allCards))
            }
        }
    }

    override val container =
        container<TransferCardContract.UIState, TransferCardContract.SideEffect>(TransferCardContract.UIState.InitState)
}