package uz.gita.m1nex.presenter.screenmodel.signin

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.Job
import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.presenter.AppViewModel
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract

interface SignInContract {
    @ScreenModelImpl(SignInScreenModelImpl::class)
    interface Model : AppViewModel<UiState,SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }
    interface Direction {
        suspend fun navigateToBack()
        suspend fun navigateToSmsConfirm(phone: String)
    }

    sealed interface UiState {
        data object Progress : UiState
        data class Error(val message: MessageData) : UiState
        data class Default(val signInRequest: SignInRequest) : UiState
    }

    sealed interface SideEffect {
        data class Toast(val message: String): SideEffect
    }

    sealed interface Intent {
        data class SignIn(val signInRequest: SignInRequest) : Intent
        data object Back : Intent
    }
}