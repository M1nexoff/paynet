package uz.gita.m1nex.presenter.screenmodel.home

import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.presenter.AppViewModel

interface HomeContract {
    @ScreenModelImpl(HomeScreenModelImpl::class)
    interface Model : AppViewModel<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }
    sealed interface UiState {
        data object DefaultState: UiState
    }

    sealed interface SideEffect {

    }

    interface Direction {

    }
    sealed interface Intent {
    }
}



