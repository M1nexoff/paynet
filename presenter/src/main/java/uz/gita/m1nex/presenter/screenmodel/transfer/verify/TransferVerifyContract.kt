package uz.gita.m1nex.presenter.screenmodel.transfer.verify

import org.orbitmvi.orbit.ContainerHost
import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.presenter.AppViewModel
import uz.gita.m1nex.presenter.screenmodel.verify.VerifyScreenModelImpl

interface TransferVerifyContract {
    @ScreenModelImpl(TransferVerifyScreenModelImpl::class)
    interface Model : AppViewModel<UiState,SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface Direction {
        suspend fun back()
    }

    sealed interface UiState {
        data object Success : UiState
        data object Progress : UiState
        data object Default : UiState
        data class Time(val time: Int) : UiState
        data class Error(val message: MessageData) : UiState
    }

    sealed interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    sealed interface Intent {
        data class TransferVerify(val code: String) : Intent
        data object Back : Intent
        data object TransferResend : Intent
    }
}