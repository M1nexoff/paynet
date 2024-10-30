package uz.gita.m1nex.presenter.screenmodel.transfer

import uz.gita.m1nex.core.ScreenModelImpl
import uz.gita.m1nex.core.data.model.transfer.LastTransferData
import uz.gita.m1nex.presenter.AppViewModel

interface TransferContract {
    @ScreenModelImpl(TransferScreenModelImpl::class)
    interface Model : AppViewModel<UIState, SideEffect> {
        fun onEventDispatchers(intent : Intent)
    }
    interface Direction {
            suspend fun toP2PScreen(cardReceiverPan: String, cardOwner: String)
    }
    sealed interface UIState {
        data object InitState  : UIState
        data class SetCardData(val it: String) : UIState
        data class SetLastTransfers(val list: List<LastTransferData>) : UIState
//        data class SetTemplates(val list: List<TemplateTable>) : UIState
    }
    sealed interface SideEffect
    sealed interface Intent {
        data class ToP2PScreen(val cardReceiverPan:String, val cardOwner:String) : Intent
        data class GetUserData(val cardNumber:String) : Intent
        data object GetLastTransfers : Intent
        data object GetTemplates : Intent
        data object ToAddTemplateScreen : Intent
    }
}