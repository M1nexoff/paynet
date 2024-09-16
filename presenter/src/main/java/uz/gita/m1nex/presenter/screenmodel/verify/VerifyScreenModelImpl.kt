package uz.gita.m1nex.presenter.screenmodel.verify

import android.util.Log
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.m1nex.core.onFailure
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.entity.data.model.request.SignInVerifyRequest
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.presenter.screenmodel.password.PasswordContract
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract
import uz.gita.m1nex.usecase.signup.AuthUseCase
import javax.inject.Inject

internal class VerifyScreenModelImpl @Inject constructor (
    private val direction: VerifyContract.Direction,
    private val authUseCase: AuthUseCase
) : VerifyContract.Model {
    init {
//        startResendCountdown()
    }
    override fun onEventDispatcher(intent: VerifyContract.Intent) = intent {
        when (intent) {
            is VerifyContract.Intent.SignInVerify -> {
                reduce { VerifyContract.UiState.Progress }
                authUseCase.signInVerify(intent.code)
                    .onSuccess {
                        direction.navigateToPassword()
                    }
                    .onFailure {
                        reduce { VerifyContract.UiState.Error(it) }
                    }
                    .launchIn(screenModelScope)

            }

            is VerifyContract.Intent.SignUpVerify -> {
                reduce { VerifyContract.UiState.Progress }
                Log.d("TTT", "onEventDispatcher: signup")
                authUseCase.signUpVerify(intent.code)
                    .onSuccess {
                        Log.d("TTT", "onEventDispatcher: success")
                        direction.navigateToPassword()
                    }
                    .onFailure {
                        Log.d("TTT", "onEventDispatcher: Fail")
                        reduce { VerifyContract.UiState.Error(it) }
                    }
                    .launchIn(screenModelScope)

            }

            VerifyContract.Intent.Back -> screenModelScope.launch { direction.back() }
            VerifyContract.Intent.SignInResend -> {
                startResendCountdown()
                reduce { VerifyContract.UiState.Progress }
                authUseCase.signInResend()
                    .onSuccess {
                        reduce { VerifyContract.UiState.Default }
                    }
                    .onFailure {
                        reduce { VerifyContract.UiState.Error(it) }
                    }
                    .launchIn(screenModelScope)
            }

            VerifyContract.Intent.SignUpResend ->  {
                startResendCountdown()
                reduce { VerifyContract.UiState.Progress }
                authUseCase.signUpResend()
                    .onSuccess {
                        reduce { VerifyContract.UiState.Default }
                    }
                    .onFailure {
                        reduce { VerifyContract.UiState.Error(it) }
                    }
                    .launchIn(screenModelScope)
            }
        }
    }

    override val container: Container<VerifyContract.UiState, VerifyContract.SideEffect> = container(getDefault())

    private fun getDefault() = VerifyContract.UiState.Default


    private fun startResendCountdown() = intent {
        screenModelScope.launch {
            for (i in 20 downTo 0) {
                reduce { VerifyContract.UiState.Time(i) }
                delay(1000L)
            }
            reduce { VerifyContract.UiState.Default }
        }
    }


}