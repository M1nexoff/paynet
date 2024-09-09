package uz.gita.m1nex.presenter.screenmodel.splash

import cafe.adriel.voyager.core.model.ScreenModel
import uz.gita.m1nex.core.ScreenModelImpl

interface SplashContract {
    @ScreenModelImpl(SplashScreenModelImpl::class)
    interface Model : ScreenModel

    interface Direction {
        suspend fun navigateToHome()
        suspend fun navigateToLogin()
        suspend fun navigateToSignUp()
    }
}