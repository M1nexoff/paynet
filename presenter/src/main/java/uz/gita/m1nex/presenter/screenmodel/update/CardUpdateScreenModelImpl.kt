package uz.gita.m1nex.presenter.screenmodel.update

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import uz.gita.m1nex.core.onFail
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.usecase.card.CardUseCase
import uz.gita.m1nex.usecase.home.HomeUseCase
import uz.gita.m1nex.usecase.signup.AuthUseCase
import javax.inject.Inject

internal class CardUpdateScreenModelImpl @Inject constructor(
    private val direction: CardUpdateContract.Direction,
    private val cardUseCase: CardUseCase
) : CardUpdateContract.Model{
    override fun onEventDispatcher(intent: CardUpdateContract.Intent) = intent {
        when (intent) {
            is CardUpdateContract.Intent.CardUpdate ->{
                cardUseCase.updateCard(intent.update).onEach {
                    it.onSuccess {
                        direction.back()
                    }.onFail {
                        direction.back()
                    }
                }.launchIn(screenModelScope)
            }
            CardUpdateContract.Intent.Back -> {
                direction.back()
            }
        }
    }

    override val container: Container<CardUpdateContract.UiState, CardUpdateContract.SideEffect> = container(getDefault())

    private fun getDefault() = CardUpdateContract.UiState.Init

}
