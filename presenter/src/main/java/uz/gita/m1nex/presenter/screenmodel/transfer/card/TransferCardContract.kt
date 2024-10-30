package uz.gita.m1nex.presenter.screenmodel.transfer.card

import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.presenter.AppViewModel

interface TransferCardContract {
    @ScreenModelImpl(TransferCardScreenModelImpl::class)
    interface Model : AppViewModel<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState{
        data object InitState:UIState
        data class CurrentCard(val cardData: CardData) : UIState
    }


    sealed interface SideEffect {
        data class Toast(val message:String):SideEffect
        data class ShowAllCardsDialog(val cardList: List<CardData>) : SideEffect
    }
    sealed interface Intent {
        data object Back : Intent
        data object GetCards : Intent
        data object ToAddCardScreen : Intent
        data object ClickAllCardsDialog : Intent
        data class Pay(val senderId :String,val receiverPan :String, val amount:Int, val pankey:Int) : Intent
        data class SelectCurrentCard(val it: CardData) : Intent
    }
    interface PayAnotherDirection {

    }

    interface Direction {
        suspend fun navigateToAddCard()
        suspend fun back()
        suspend fun navigateToVerify(phone: String)
    }
}