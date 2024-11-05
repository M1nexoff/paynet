package uz.gita.m1nex.presenter.screenmodel.user

import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.core.data.model.FullInfoResponse
import uz.gita.m1nex.core.data.model.UpdateInfoRequest
import uz.gita.m1nex.presenter.AppViewModel

sealed interface UserDataContract {
    @ScreenModelImpl(UserDataScreenModelImpl::class)
    sealed interface Model : AppViewModel<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }
    sealed interface SideEffect{

    }
    sealed interface Intent{
        object Back : Intent
        data object GetData: Intent
        data class UpdateInfo(val data: UpdateInfoRequest): Intent
    }
    sealed interface UiState{
        data object Init : UiState
        data class Data(val user: FullInfoResponse): UiState
    }
    interface Direction{
        suspend fun back()
    }

}