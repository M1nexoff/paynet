package uz.gita.m1nex.presenter.screenmodel.card

import org.orbitmvi.orbit.ContainerHost
import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.presenter.AppViewModel

interface CardContract {
    @ScreenModelImpl(CardScreenModelImpl::class)
    sealed interface Model : AppViewModel<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface Direction {
        suspend fun back()
    }

    sealed interface UIState {
        data object InitState  : UIState
        data class SetCardData(val it: List<CardData>) : UIState
    }

    sealed interface SideEffect {
        data class OpenDialog(val cardId:String,val pan:String):SideEffect
    }

    sealed interface Intent {
        data class OpenCardInfo(val data:CardData) : Intent
        data class DeleteCard(val data:String) : Intent
        data class OpenDialog(val cardId:String,val pan:String) : Intent
        data class ToP2PScreen(val pan: String, val owner: String) : Intent
        data object NavigateBack : Intent
        data object GetCards : Intent
        data object Back : Intent
    }
}