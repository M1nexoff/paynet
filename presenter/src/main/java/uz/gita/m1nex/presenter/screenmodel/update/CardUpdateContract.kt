package uz.gita.m1nex.presenter.screenmodel.update

import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.core.data.model.FullInfoResponse
import uz.gita.m1nex.core.data.model.UpdateInfoRequest
import uz.gita.m1nex.core.data.model.card.UpdateCardRequest
import uz.gita.m1nex.presenter.AppViewModel
interface CardUpdateContract {
    @ScreenModelImpl(CardUpdateScreenModelImpl::class)
    sealed interface Model : AppViewModel<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface SideEffect {

    }

    sealed interface Intent {
        data object Back : Intent
        data class CardUpdate(val update: UpdateCardRequest): Intent
    }

    sealed interface UiState {
        data object Init : UiState
    }

    interface Direction {
        suspend fun back()
    }

}