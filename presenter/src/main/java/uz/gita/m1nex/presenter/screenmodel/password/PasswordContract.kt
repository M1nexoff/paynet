package uz.gita.m1nex.presenter.screenmodel.password

import androidx.annotation.StringRes
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.Job
import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.presenter.AppViewModel
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract

interface PasswordContract {

    @ScreenModelImpl(PasswordScreenModelImpl::class)
    interface Model : AppViewModel<UiState,SignUpContract.SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface Direction {
        suspend fun navigateToHomeScreen()
        suspend fun back()
    }

    sealed interface UiState {
        data object Default : UiState
        data object Repeat : UiState
        data object Progress : UiState
        data class Error(val message: String) : UiState
        data object Success : UiState
    }

    sealed interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    sealed interface Intent {
        data class CheckPassword(val password: String) : Intent
        data class CreatePassword(val password: String) : Intent
        data object Back : Intent
    }
}
