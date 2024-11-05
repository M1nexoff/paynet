package uz.gita.m1nex.presenter.screenmodel.home.tab.main

import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.presenter.AppViewModel

interface MainContract {
    @ScreenModelImpl(MainScreenModelImpl::class)
    interface Model : AppViewModel<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
        fun onInit()
    }

    sealed interface UiState {
        data object Default : UiState
        data class BasicState(val phone: String = "", val balance: Int = 0): UiState
        data class CardsState(val list: List<CardData>) : UiState
    }

    interface Direction {
        suspend fun openPaynetCardScreen(data: CardData)
        suspend fun openProfile()
        suspend fun openAddScreen()
        suspend fun openTransferScreen()
        suspend fun openNotifications()
        suspend fun openWhatIsThisScreen()
//        suspend fun openCardsScreen(list: List<CardData>)
    }

    sealed interface SideEffect {
        data class ShowAllCardsDialog(val cardList: List<CardData>) : SideEffect
        data object AddCardDialog : SideEffect
    }

    sealed interface Intent {
        data object OpenProfile : Intent
        data object OpenAddScreen : Intent
        data object OpenTransferScreen : Intent
        data object OpenNotifications : Intent
        data object OpenWhatIsThisScreen : Intent
        data class OpenPaynetCardScreen(val data: CardData) : Intent
        data class OpenAllCardsScreen(val cards: List<CardData>) : Intent
        data object OpenCardsScreen : Intent
        data object GetCardsData : Intent
        data object GetBalance : Intent
        data object GetBasicInfo : Intent
        data object AddCart : Intent
    }
}