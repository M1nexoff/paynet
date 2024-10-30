package uz.gita.m1nex.presenter.screenmodel.history

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.core.data.model.Child
import uz.gita.m1nex.presenter.AppViewModel

interface HistoryContract {
    @ScreenModelImpl(HistoryScreenModelImpl::class)
    interface Model : AppViewModel<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
        fun getCardHistory(): Flow<PagingData<Child>>
    }
    sealed interface Intent {
        data object GetHistory : Intent
        data object GetBalance : Intent
        data class OpenPaymentMethod(val child: Child) : Intent
    }

    sealed interface SideEffect {
        data class OpenDialog(val child: Child) : SideEffect

    }

    sealed interface UiState {
        data class Balance(val balance:String):UiState

        data object Init : UiState
    }
}