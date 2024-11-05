package uz.gita.m1nex.presenter.screenmodel.profile

import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.presenter.AppViewModel

interface ProfileContract {
    @ScreenModelImpl(ProfileScreenModelImpl::class)
    interface Model : AppViewModel<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }
    interface Direction {
        suspend fun navigateToBack()
        suspend fun navigateToSignUp()
        suspend fun navigateToUpdate()
    }

    sealed interface UiState {
        data object Default : UiState
        data object LogOut: UiState
        data class Error(val message: MessageData, val isNetworkError: Boolean = false) : UiState
    }


    sealed interface SideEffect {
        data object OpenRankSheet : SideEffect, UiState
        data object OpenInfoSheet : SideEffect
        data object OpenCallSheet : SideEffect

    }


    sealed interface Intent {
        data object Back : Intent
        data object LogOut : Intent
        data object Map : Intent
        data object About : Intent
        data object Info : Intent
        data object Support : Intent
        data object Settings : Intent
        data object Rank : Intent
        data object UserStatus : Intent
        data object CallCenter : Intent
    }
}