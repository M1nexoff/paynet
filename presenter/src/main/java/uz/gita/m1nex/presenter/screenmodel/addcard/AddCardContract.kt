package uz.gita.m1nex.presenter.screenmodel.addcard

import org.orbitmvi.orbit.ContainerHost
import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.presenter.AppViewModel
import uz.gita.m1nex.presenter.screenmodel.profile.ProfileScreenModelImpl

sealed interface AddCardContract {
    @ScreenModelImpl(AddCardScreenModelImpl::class)
    sealed interface Model: AppViewModel<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }
    sealed interface  Intent {
        data object Back: Intent
        data class Continue(val pan: String, val expiredYear: String,val expiredMonth: String,val name: String = "") : Intent
    }

    sealed interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    sealed interface UIState {
        data class Error(val message: MessageData) : UIState
        data object InitStat : UIState

    }

    interface Direction {
        suspend fun backToMainScreen()
    }
}