package uz.gita.m1nex.presenter.screenmodel.user

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.m1nex.core.onFail
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.usecase.home.HomeUseCase
import uz.gita.m1nex.usecase.signup.AuthUseCase
import javax.inject.Inject

internal class UserDataScreenModelImpl @Inject constructor(
    private val direction: UserDataContract.Direction,
    private val authUseCase: AuthUseCase,
    private val homeUseCase: HomeUseCase
) : UserDataContract.Model{
    override fun onEventDispatcher(intent: UserDataContract.Intent) = intent {
        when (intent) {
            UserDataContract.Intent.Back -> {
                direction.back()
            }
            is UserDataContract.Intent.UpdateInfo -> {
                homeUseCase.updateUserInfo(intent.data).onEach {
                    it.onSuccess {
                        direction.back()
                    }.onFail {
                        direction.back()
                    }
                }.launchIn(screenModelScope)
            }

            UserDataContract.Intent.GetData -> {
                homeUseCase.getFullUserInfo().onEach {
                    it.onSuccess {
                        reduce { UserDataContract.UiState.Data(this@onSuccess) }
                    }
                }.launchIn(screenModelScope)
            }
        }
    }

    override val container: Container<UserDataContract.UiState, UserDataContract.SideEffect> = container(getDefault())

    private fun getDefault() = UserDataContract.UiState.Init

}
