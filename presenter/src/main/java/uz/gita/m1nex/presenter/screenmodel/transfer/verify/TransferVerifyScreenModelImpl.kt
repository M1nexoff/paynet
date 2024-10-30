package uz.gita.m1nex.presenter.screenmodel.transfer.verify

import android.util.Log
import cafe.adriel.voyager.core.model.screenModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.m1nex.core.onFailure
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.usecase.transfer.TransferUseCase
import javax.inject.Inject


internal class TransferVerifyScreenModelImpl @Inject constructor(
    private val direction: TransferVerifyContract.Direction,
    private val transferUseCase: TransferUseCase
    ) : TransferVerifyContract.Model {

    override fun onEventDispatcher(intent: TransferVerifyContract.Intent) = intent {
        when (intent) {
            is TransferVerifyContract.Intent.TransferVerify -> {
                reduce { TransferVerifyContract.UiState.Progress }
                transferUseCase.transferVerify(intent.code)
                    .onSuccess {
                        reduce { TransferVerifyContract.UiState.Success }
                    }
                    .onFailure {
                        reduce { TransferVerifyContract.UiState.Error(it) }
                    }
                    .launchIn(screenModelScope)

            }

            is TransferVerifyContract.Intent.TransferResend -> {
                reduce { TransferVerifyContract.UiState.Progress }
                Log.d("TTT", "onEventDispatcher: signup")
                transferUseCase.transferResend()
                    .onSuccess {
                        Log.d("TTT", "onEventDispatcher: success")
                        startResendCountdown()
                    }
                    .onFailure {
                        Log.d("TTT", "onEventDispatcher: Fail")
                        reduce { TransferVerifyContract.UiState.Error(it) }
                    }
                    .launchIn(screenModelScope)

            }
            TransferVerifyContract.Intent.Back -> screenModelScope.launch { direction.back() }
        }
    }

    override val container: Container<TransferVerifyContract.UiState, TransferVerifyContract.SideEffect> = container(getDefault())

    private fun getDefault() = TransferVerifyContract.UiState.Default


    private fun startResendCountdown() = intent {
        screenModelScope.launch {
            for (i in 20 downTo 0) {
                reduce { TransferVerifyContract.UiState.Time(i) }
                delay(1000L)
            }
            reduce { TransferVerifyContract.UiState.Default }
        }
    }


}