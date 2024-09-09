package uz.gita.m1nex.presenter.screenmodel.signup

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.m1nex.core.onFailure
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.usecase.signup.AuthUseCase
import javax.inject.Inject

class SignUpScreenModelImpl @Inject constructor(
    private val direction: SignUpContract.Direction,
    private val signUpUseCase: AuthUseCase
) : SignUpContract.Model {
    override fun onEventDispatcher(intent: SignUpContract.Intent) = intent {
        when (intent) {
            is SignUpContract.Intent.SignUp -> {
                reduce { SignUpContract.UiState.Progress }

                signUpUseCase.signUp(intent.signUpRequest)
                    .onSuccess {
                        direction.navigateToSmsConfirm(intent.signUpRequest.phone)
                    }
                    .onFailure {
                        reduce { SignUpContract.UiState.Error(it) }
                    }
                    .launchIn(screenModelScope)
            }

            SignUpContract.Intent.SignIn -> {
                screenModelScope.launch { direction.navigateToSignIn() }
            }
        }

    }


    override val container: Container<SignUpContract.UiState, SignUpContract.SideEffect> =
        container(getDefault())

    private fun getDefault() = SignUpContract.UiState.Default(SignUpRequest("", "", "", "", "", ""))

}
