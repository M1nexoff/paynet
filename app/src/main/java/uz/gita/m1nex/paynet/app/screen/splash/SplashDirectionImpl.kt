package uz.gita.m1nex.paynet.app.screen.splash

import android.util.Log
import uz.gita.m1nex.paynet.app.screen.home.HomeScreen
import uz.gita.m1nex.paynet.app.screen.signin.SignInScreen
import uz.gita.m1nex.paynet.app.screen.signup.SignUpScreen
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.splash.SplashContract
import javax.inject.Inject

class SplashDirectionImpl @Inject constructor(private val navigator: AppNavigator) : SplashContract.Direction {
    override suspend fun navigateToHome() {
        navigator.replace(HomeScreen())
    }

    override suspend fun navigateToLogin() {
        navigator.replace(SignUpScreen())
    }

    override suspend fun navigateToSignUp() {
        Log.d("TTT", "navigateToSignUp")
        navigator.replace(SignUpScreen())
    }

}