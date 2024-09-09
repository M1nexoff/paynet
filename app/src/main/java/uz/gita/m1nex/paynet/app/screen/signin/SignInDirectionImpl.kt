package uz.gita.m1nex.paynet.app.screen.signin

import uz.gita.m1nex.paynet.app.screen.signin.SignInScreen
import uz.gita.m1nex.paynet.app.screen.verify.VerifyScreen
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.signin.SignInContract
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract
import uz.gita.m1nex.presenter.screenmodel.splash.SplashContract
import javax.inject.Inject

class SignInDirectionImpl @Inject constructor(private val navigator: AppNavigator) : SignInContract.Direction {
    override suspend fun navigateToBack() {
        navigator.back()
    }

    override suspend fun navigateToSmsConfirm(phone: String) {
        navigator.navigateTo(VerifyScreen(phone,true))
    }
}