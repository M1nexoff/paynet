package uz.gita.m1nex.presenter.screenmodel.addcard

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.m1nex.core.data.model.card.AddCard
import uz.gita.m1nex.core.onFailure
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.usecase.card.CardUseCase
import javax.inject.Inject

class AddCardScreenModelImpl @Inject constructor(
    private val cardUseCase: CardUseCase,
    private val direction: AddCardContract.Direction
) : AddCardContract.Model {
    override fun onEventDispatcher(intent: AddCardContract.Intent) = intent {
        when (intent) {
            is AddCardContract.Intent.Continue -> {

                cardUseCase.addCard(
                    AddCard(
                        pan = intent.pan,
                        expiredYear = intent.expiredYear,
                        expiredMonth = intent.expiredMonth,
                        "Personal"
                    )
                )
                    .onSuccess {
                        direction.backToMainScreen()
                    }.onFailure {
                        reduce { AddCardContract.UIState.Error(it) }
                    }.launchIn(screenModelScope)
            }

            AddCardContract.Intent.Back -> {
                direction.backToMainScreen()
            }
        }
    }

    override val container: Container<AddCardContract.UIState, AddCardContract.SideEffect> = container(AddCardContract.UIState.InitStat)
}