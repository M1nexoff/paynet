package uz.gita.m1nex.presenter.screenmodel.password

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.m1nex.core.onFailure
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.presenter.R
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract
import uz.gita.m1nex.usecase.password.PasswordUseCase
import javax.inject.Inject

class PasswordScreenModelImpl @Inject constructor(
    private val direction: PasswordContract.Direction,
    private val passwordUseCase: PasswordUseCase
) : PasswordContract.Model {

    override fun onEventDispatcher(intent: PasswordContract.Intent) = intent {
        when (intent) {
            is PasswordContract.Intent.CreatePassword -> {
                reduce { PasswordContract.UiState.Progress }
                if (password.isEmpty()) {
                    password = intent.password
                    reduce { PasswordContract.UiState.Repeat }
                } else {
                    if (intent.password == password) {
                        passwordUseCase.create(intent.password)
                            .onSuccess {
                                reduce { PasswordContract.UiState.Success }
                                direction.navigateToHomeScreen()
                            }.launchIn(screenModelScope)
                    } else {
                        reduce { PasswordContract.UiState.Default }
                        password = ""
                    }
                }
            }

            PasswordContract.Intent.Back -> screenModelScope.launch { direction.back() }
            is PasswordContract.Intent.CheckPassword -> {
                reduce { PasswordContract.UiState.Progress }
                passwordUseCase.check(intent.password)
                    .onSuccess {
                        reduce { PasswordContract.UiState.Success }
                        direction.navigateToHomeScreen()
                    }
                    .onFailure {
                        reduce { PasswordContract.UiState.Error("wrong") }
                    }
                    .launchIn(screenModelScope)
            }
        }
    }

    private var password = ""
    override val container: Container<PasswordContract.UiState, SignUpContract.SideEffect> =
        container(getDefault())

    private fun getDefault() = PasswordContract.UiState.Default
}
