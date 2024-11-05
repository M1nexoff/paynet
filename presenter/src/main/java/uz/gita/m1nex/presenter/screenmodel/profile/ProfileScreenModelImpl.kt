package uz.gita.m1nex.presenter.screenmodel.profile

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import uz.gita.m1nex.core.onFail
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.usecase.home.HomeUseCase
import uz.gita.m1nex.usecase.signup.AuthUseCase
import javax.inject.Inject

internal class ProfileScreenModelImpl @Inject constructor(
    private val direction: ProfileContract.Direction,
    private val authUseCase: AuthUseCase,
    private val homeUseCase: HomeUseCase
) : ProfileContract.Model{
    override fun onEventDispatcher(intent: ProfileContract.Intent) = intent {
        when (intent) {
            ProfileContract.Intent.About -> {
                postSideEffect(ProfileContract.SideEffect.OpenInfoSheet)
            }
            ProfileContract.Intent.CallCenter -> {
                postSideEffect(ProfileContract.SideEffect.OpenCallSheet)
            }
            ProfileContract.Intent.Info -> {
                postSideEffect(ProfileContract.SideEffect.OpenInfoSheet)
            }
            ProfileContract.Intent.LogOut -> {
                authUseCase.logout().onSuccess {
                    direction.navigateToSignUp()
                }.launchIn(screenModelScope)
            }
            ProfileContract.Intent.Map -> {
                postSideEffect(ProfileContract.SideEffect.OpenInfoSheet)
            }
            ProfileContract.Intent.Rank -> {
                postSideEffect(ProfileContract.SideEffect.OpenRankSheet)
            }
            ProfileContract.Intent.Settings -> {
            }
            ProfileContract.Intent.Support -> {
            }
            ProfileContract.Intent.UserStatus -> {
                homeUseCase.isVerified().onEach { 
                    it.onSuccess {
                        direction.navigateToUpdate()
                    }
                }.launchIn(screenModelScope)
            }
            ProfileContract.Intent.Back -> {
                direction.navigateToBack()
            }
        }
    }

    override val container: Container<ProfileContract.UiState, ProfileContract.SideEffect> = container(getDefault())

    private fun getDefault() = ProfileContract.UiState.Default

}
