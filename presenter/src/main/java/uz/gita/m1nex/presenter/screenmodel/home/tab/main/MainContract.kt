package uz.gita.m1nex.presenter.screenmodel.home.tab.main

import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.presenter.AppViewModel
import uz.gita.m1nex.presenter.screenmodel.home.HomeScreenModelImpl

interface MainContract {
    @ScreenModelImpl(MainScreenModelImpl::class)
    interface Model : AppViewModel<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UiState {
        data object Default : UiState
        data class BasicInfo(val userName: String,val balance: Int) : UiState
    }

    sealed interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    sealed interface Intent {
        data object Back : Intent
    }
}