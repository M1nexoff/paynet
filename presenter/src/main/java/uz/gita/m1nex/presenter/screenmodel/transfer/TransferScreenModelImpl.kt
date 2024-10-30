package uz.gita.m1nex.presenter.screenmodel.transfer

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.m1nex.core.asSuccess
import uz.gita.m1nex.core.asText
import uz.gita.m1nex.core.onFail
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.usecase.transfer.TransferUseCase
import javax.inject.Inject


internal class TransferScreenModelImpl @Inject constructor(
    private val direction: TransferContract.Direction,
    private val transferUseCase: TransferUseCase
) : TransferContract.Model {
    override fun onEventDispatchers(intent: TransferContract.Intent) = intent {
        when (intent) {
            is TransferContract.Intent.ToP2PScreen -> {
                direction.toP2PScreen(intent.cardReceiverPan, intent.cardOwner)
            }
            is TransferContract.Intent.GetUserData -> {
                transferUseCase.getUserData(intent.cardNumber).onEach {
                    it.onSuccess {
                        reduce { TransferContract.UIState.SetCardData(it.asSuccess.data.message) }
//                        it.pan.myLog()
                    }
                }.launchIn(screenModelScope)
//                repository.getUserDataByPan(intent.cardNumber).onEach {
//                    it.onSuccess {
//                        reduce { TransferContract.UIState.SetCardData(it.pan) }
//                        it.pan.myLog()
//                    }.onFailure {
//
//                    }
//                }.launchIn(screenModelScope)
            }
            is TransferContract.Intent.GetLastTransfers -> {
//                "GetLastTransfers ga murojaat".myLog()
//                reduce { TransferContract.UIState.SetLastTransfers(cardRepository.getLastTransfersLocal().reversed()) }
            }
            is TransferContract.Intent.GetTemplates -> {
                screenModelScope.launch {
                    delay(200)
//                    reduce { TransferContract.UIState.SetTemplates(cardRepository.getTemplatesLocal().reversed()) }
                }

            }
            is TransferContract.Intent.ToAddTemplateScreen -> {
//                navigator.navigateTo(ChooseDataScreen())
            }
        }
    }

    override val container =
        container<TransferContract.UIState, TransferContract.SideEffect>(TransferContract.UIState.InitState)
}