package uz.gita.m1nex.presenter.screenmodel.signin

import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.m1nex.core.onFailure
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract
import uz.gita.m1nex.usecase.signup.AuthUseCase
import javax.inject.Inject

internal class SignInScreenModelImpl @Inject constructor(
    private val direction: SignInContract.Direction,
    private val authUseCase: AuthUseCase
) : SignInContract.Model{
    override fun onEventDispatcher(intent: SignInContract.Intent) = intent {
        when (intent) {
            is SignInContract.Intent.SignIn -> {
                reduce { SignInContract.UiState.Progress }

                authUseCase.signIn(intent.signInRequest)
                    .onSuccess {
                        direction.navigateToSmsConfirm(intent.signInRequest.phone)
                    }
                    .onFailure {
                        reduce { SignInContract.UiState.Error(it) }
                    }
                    .launchIn(screenModelScope)
            }

            SignInContract.Intent.Back -> {
                direction.navigateToBack()
            }

            SignInContract.Intent.Language ->{}
        }
    }

    override val container: Container<SignInContract.UiState, SignInContract.SideEffect> = container(getDefault())

    private fun getDefault() = SignInContract.UiState.Default(SignInRequest("",""))

}
