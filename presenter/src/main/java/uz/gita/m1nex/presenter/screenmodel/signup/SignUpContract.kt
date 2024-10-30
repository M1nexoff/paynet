package uz.gita.m1nex.presenter.screenmodel.signup

import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.core.data.model.sign.SignUp
import uz.gita.m1nex.presenter.AppViewModel

interface SignUpContract {
    @ScreenModelImpl(SignUpScreenModelImpl::class)
    interface Model : AppViewModel<UiState,SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }
    interface Direction {
        suspend fun navigateToSignIn()
        suspend fun navigateToSmsConfirm(phone: String)
    }

    sealed interface UiState {
        data object Progress : UiState
        data class Error(val message: MessageData) : UiState
        data class Default(val signUpRequest: SignUp) : UiState
    }

    sealed interface SideEffect {
        data class Toast(val message: String): SideEffect
    }

    sealed interface Intent {
        data class SignUp(val signUpRequest: uz.gita.m1nex.core.data.model.sign.SignUp) : Intent
        data object SignIn : Intent
    }
}