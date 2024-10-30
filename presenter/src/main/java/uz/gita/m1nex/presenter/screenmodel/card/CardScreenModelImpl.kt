package uz.gita.m1nex.presenter.screenmodel.card

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import uz.gita.m1nex.usecase.card.CardUseCase
import javax.inject.Inject

class CardScreenModelImpl @Inject constructor(
    private val cardsUseCase: CardUseCase,
    private val direction: CardContract.Direction
) : CardContract.Model {
    init {


    }
    override fun onEventDispatcher(intent: CardContract.Intent) {
        when (intent) {
            is CardContract.Intent.GetCards -> {

            }
            is CardContract.Intent.DeleteCard ->{

            }
            CardContract.Intent.NavigateBack ->{

            }
            is CardContract.Intent.OpenCardInfo ->{

            }
            is CardContract.Intent.OpenDialog ->{

            }
            is CardContract.Intent.ToP2PScreen ->{

            }

            CardContract.Intent.Back -> {
                screenModelScope.launch {
                    direction.back()
                }
            }
        }
    }

    override val container: Container<CardContract.UIState, CardContract.SideEffect> = container(CardContract.UIState.InitState)

}