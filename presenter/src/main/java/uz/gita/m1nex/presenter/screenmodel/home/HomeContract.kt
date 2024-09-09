package uz.gita.m1nex.presenter.screenmodel.home

import kotlinx.coroutines.Job
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.presenter.AppViewModel

interface HomeContract {
    @ScreenModelImpl(HomeScreenModelImpl::class)
    interface Model : AppViewModel<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface Direction {
        suspend fun back()
    }

    sealed interface UiState {
        data object Default : UiState
    }

    sealed interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    sealed interface Intent {
        data object Back : Intent
    }
}
