package uz.gita.m1nex.presenter.screenmodel.signin

import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.presenter.AppViewModel

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
        data class Error(val message: MessageData, val isNetworkError: Boolean = false) : UiState
        data class Default(val signInRequest: uz.gita.m1nex.core.data.model.sign.SignIn) : UiState
    }


    sealed interface SideEffect {
    }

    sealed interface Intent {
        data object Language : Intent
        data class SignIn(val signInRequest: uz.gita.m1nex.core.data.model.sign.SignIn) : Intent
        data object Back : Intent
    }
}