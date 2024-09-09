package uz.gita.m1nex.presenter.screenmodel.verify

import cafe.adriel.voyager.core.model.ScreenModel
import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.presenter.AppViewModel
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpScreenModelImpl
import java.sql.Time

interface VerifyContract {

    @ScreenModelImpl(VerifyScreenModelImpl::class)
    interface Model : AppViewModel<UiState,SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface Direction {
        suspend fun navigateToPassword()
        suspend fun back()
    }

    sealed interface UiState {
        data object Progress : UiState
        data object Default : UiState
        data class Time(val time: Int) : UiState
        data class Error(val message: MessageData) : UiState
    }

    sealed interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    sealed interface Intent {
        data class SignInVerify(val code: String) : Intent
        data class SignUpVerify(val code: String) : Intent
        data object Back : Intent
        data object SignInResend : Intent
        data object SignUpResend : Intent
    }
}