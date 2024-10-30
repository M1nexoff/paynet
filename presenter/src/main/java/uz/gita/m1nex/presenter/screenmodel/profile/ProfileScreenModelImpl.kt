package uz.gita.m1nex.presenter.screenmodel.profile

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.usecase.signup.AuthUseCase
import javax.inject.Inject

internal class ProfileScreenModelImpl @Inject constructor(
    private val direction: ProfileContract.Direction,
    private val authUseCase: AuthUseCase
) : ProfileContract.Model{
    override fun onEventDispatcher(intent: ProfileContract.Intent) = intent {
        when (intent) {
            ProfileContract.Intent.About -> {
            }
            ProfileContract.Intent.CallCenter -> {
            }
            ProfileContract.Intent.Info -> {
            }
            ProfileContract.Intent.LogOut -> {
                authUseCase.logout().onSuccess {
                    direction.navigateToSignUp()
                }.launchIn(screenModelScope)
            }
            ProfileContract.Intent.Map -> {
            }
            ProfileContract.Intent.Rank -> {
            }
            ProfileContract.Intent.Settings -> {
            }
            ProfileContract.Intent.Support -> {
            }
            ProfileContract.Intent.UserStatus -> {
            }
            ProfileContract.Intent.Back -> {
                direction.navigateToBack()
            }
        }
    }

    override val container: Container<ProfileContract.UiState, ProfileContract.SideEffect> = container(getDefault())

    private fun getDefault() = ProfileContract.UiState.Default

}
