package uz.gita.m1nex.presenter.screenmodel.splash

import cafe.adriel.voyager.core.model.ScreenModel
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.presenter.AppViewModel

interface SplashContract {
    @ScreenModelImpl(SplashScreenModelImpl::class)
    interface Model : AppViewModel<UIState,SideEffect>
    sealed interface UIState {
        data object Default : UIState
    }

    sealed interface SideEffect {

    }

    interface Direction {
        suspend fun navigateToHome()
        suspend fun navigateToLogin()
        suspend fun navigateToSignUp()
    }
}