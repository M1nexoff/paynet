package uz.gita.m1nex.paynet.app.screen.signup

import uz.gita.m1nex.paynet.app.screen.signin.SignInScreen
import uz.gita.m1nex.paynet.app.screen.verify.VerifyScreen
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract
import uz.gita.m1nex.presenter.screenmodel.splash.SplashContract
import javax.inject.Inject

class SignUpDirectionImpl @Inject constructor(private val navigator: AppNavigator) : SignUpContract.Direction {
    override suspend fun navigateToSignIn() {
        navigator.navigateTo(SignInScreen())
    }

    override suspend fun navigateToSmsConfirm(phone: String) {
        navigator.navigateTo(VerifyScreen(phone,false))
    }
}