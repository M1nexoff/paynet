package uz.gita.m1nex.presenter.screenmodel.card

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.m1nex.core.onFail
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.usecase.card.CardUseCase
import javax.inject.Inject

class CardScreenModelImpl @Inject constructor(
    private val cardsUseCase: CardUseCase,
    private val direction: CardContract.Direction
) : CardContract.Model {

    override fun onEventDispatcher(intent: CardContract.Intent) = intent {
        when (intent) {
            is CardContract.Intent.GetCards -> {

            }
            is CardContract.Intent.DeleteCard ->{
                cardsUseCase.deleteCard(intent.data).onEach {
                    it.onSuccess {
                        direction.backUntilHome()
                    }.onFail {

                    }
                }.launchIn(screenModelScope)
            }
            is CardContract.Intent.OpenCardInfo ->{
            }
            is CardContract.Intent.OpenDialog ->{
                postSideEffect( CardContract.SideEffect.OpenDialog(intent.cardId,intent.pan) )
            }
            is CardContract.Intent.ToP2PScreen ->{
                direction.openP2PScreen(intent.pan,intent.owner)
            }

            CardContract.Intent.Back -> {
                screenModelScope.launch {
                    direction.back()
                }
            }
            is CardContract.Intent.OpenUpdate->{
                direction.openUpdate(intent.card)
            }
            else -> {}
        }
    }

    override val container: Container<CardContract.UIState, CardContract.SideEffect> = container(CardContract.UIState.InitState)

}