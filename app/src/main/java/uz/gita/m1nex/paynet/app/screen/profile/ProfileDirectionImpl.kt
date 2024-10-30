package uz.gita.m1nex.paynet.app.screen.profile

import uz.gita.m1nex.paynet.app.screen.signup.SignUpScreen
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.profile.ProfileContract
import javax.inject.Inject

class ProfileDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : ProfileContract.Direction {
    override suspend fun navigateToBack() {
        navigator.back()
    }

    override suspend fun navigateToSignUp() {
        navigator.replaceAll(SignUpScreen())
    }
}