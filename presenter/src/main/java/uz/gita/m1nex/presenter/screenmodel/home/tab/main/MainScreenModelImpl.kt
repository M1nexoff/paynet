package uz.gita.m1nex.presenter.screenmodel.home.tab.main

import android.util.Log
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.m1nex.core.getText
import uz.gita.m1nex.core.onFailure
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.presenter.screenmodel.home.HomeContract
import uz.gita.m1nex.presenter.screenmodel.transfer.card.TransferCardContract
import uz.gita.m1nex.usecase.card.CardUseCase
import uz.gita.m1nex.usecase.home.HomeUseCase
import javax.inject.Inject
import kotlin.math.log

internal class MainScreenModelImpl @Inject constructor(
    private val direction: MainContract.Direction,
    private val homeUseCase: HomeUseCase,
    private val cardUseCase: CardUseCase
) : MainContract.Model {
    init {
        Log.d("TTT", "MainScreenInit: init")
        homeUseCase.getBasicUserInfo().onSuccess {
            val firstName = it.firstName
            intent { reduce { MainContract.UiState.BasicState(it.firstName) } }
            homeUseCase.getTotalBalance().onSuccess {
                intent { reduce { MainContract.UiState.BasicState(firstName, it) } }
            }.onFailure {
                Log.d("TTT", "MainScreenInit: firstName ${it.toString()}")
            }.launchIn(screenModelScope)
            Log.d("TTT", "MainScreenInit: firstName ${it.firstName}")
        }.onFailure {
            Log.d("TTT", "MainScreenInit: firstName ${it.toString()}")
        }.launchIn(screenModelScope)
        cardUseCase.getCards().onSuccess {
            intent { reduce { MainContract.UiState.CardsState(it) } }
        }.onFailure {
            Log.d("TTT", "MainScreenModelImpl: ${it.toString()}")
        }.launchIn(screenModelScope)
    }
    override fun onEventDispatcher(intent: MainContract.Intent) = intent {
        when (intent) {
            MainContract.Intent.GetBalance -> {

            }
            MainContract.Intent.GetCardsData -> {

            }
            MainContract.Intent.OpenAddScreen -> {
                direction.openAddScreen()
            }
            MainContract.Intent.OpenCardsScreen -> {

            }
            MainContract.Intent.OpenNotifications -> {

            }
            is MainContract.Intent.OpenPaynetCardScreen -> {
                direction.openPaynetCardScreen(intent.data)
            }
            MainContract.Intent.OpenProfile -> {
                direction.openProfile()
            }
            MainContract.Intent.OpenTransferScreen -> {

            }
            MainContract.Intent.OpenWhatIsThisScreen -> {

            }

            MainContract.Intent.GetBasicInfo -> {
                homeUseCase.getBasicUserInfo().onSuccess {
                    val firstName = it.firstName
                    intent { reduce { MainContract.UiState.BasicState(it.firstName) } }
                    homeUseCase.getTotalBalance().onSuccess {
                        intent { reduce { MainContract.UiState.BasicState(firstName, it) } }
                    }.onFailure { Log.d("TTT", "MainScreenInit: error ${it.toString()}") }.launchIn(screenModelScope)
                }.onFailure { Log.d("TTT", "MainScreenInit: error ${it.toString()}") }.launchIn(screenModelScope)
                cardUseCase.getCards().onSuccess {
                    intent { reduce { MainContract.UiState.CardsState(it) } }
                }.onFailure { Log.d("TTT", "MainScreenModelImpl: ${it.toString()}") }.launchIn(screenModelScope)
            }

            MainContract.Intent.AddCart -> {
                postSideEffect(MainContract.SideEffect.AddCardDialog)
            }

            is MainContract.Intent.OpenAllCardsScreen -> {
                direction.openCardsScreen(intent.cards)
            }
        }
    }

    override val container: Container<MainContract.UiState, MainContract.SideEffect> = container(getDefault())

    private fun getDefault() = MainContract.UiState.Default
}