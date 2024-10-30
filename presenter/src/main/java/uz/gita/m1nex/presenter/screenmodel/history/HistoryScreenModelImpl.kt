package uz.gita.m1nex.presenter.screenmodel.history

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.m1nex.core.data.model.Child
import uz.gita.m1nex.core.onFail
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.core.success
import uz.gita.m1nex.usecase.history.HistoryUseCase
import uz.gita.m1nex.usecase.home.HomeUseCase
import javax.inject.Inject

class HistoryScreenModelImpl @Inject constructor(
    private val useCase: HistoryUseCase,
    private val homeUseCase: HomeUseCase
) : HistoryContract.Model {
    override fun onEventDispatcher(intent: HistoryContract.Intent) = intent {
        when(intent){
            HistoryContract.Intent.GetBalance -> {
                homeUseCase.getTotalBalance().onEach {
                    it.success {
                        reduce { HistoryContract.UiState.Balance(it.toString()) }
                    }.onFail {

                    }
                }.launchIn(screenModelScope)
            }
            HistoryContract.Intent.GetHistory -> {

            }
            is HistoryContract.Intent.OpenPaymentMethod -> {
                postSideEffect(HistoryContract.SideEffect.OpenDialog(intent.child))
            }
        }

    }

    override fun getCardHistory(): Flow<PagingData<Child>> = useCase.getHistory()

    override val container: Container<HistoryContract.UiState, HistoryContract.SideEffect> = container(HistoryContract.UiState.Init)
}