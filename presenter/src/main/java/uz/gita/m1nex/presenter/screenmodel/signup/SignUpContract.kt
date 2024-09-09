package uz.gita.m1nex.presenter.screenmodel.signup

import android.view.WindowInsets.Side
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.Job
import org.orbitmvi.orbit.ContainerHost
import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
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
        data class Default(val signUpRequest: SignUpRequest) : UiState
    }

    sealed interface SideEffect {
        data class Toast(val message: String): SideEffect
    }

    sealed interface Intent {
        data class SignUp(val signUpRequest: SignUpRequest) : Intent
        data object SignIn : Intent
    }
}