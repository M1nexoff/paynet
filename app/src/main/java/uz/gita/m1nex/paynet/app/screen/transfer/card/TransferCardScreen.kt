package uz.gita.m1nex.paynet.app.screen.transfer.card

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.core.previewStateOf
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.screen.addcard.TopSection
import uz.gita.m1nex.paynet.app.ui.dialog.CardsSheetDialog
import uz.gita.m1nex.paynet.app.ui.theme.component.AppButton
import uz.gita.m1nex.paynet.app.ui.theme.component.CardP2PSendItem
import uz.gita.m1nex.paynet.app.ui.theme.component.CardP2PWithCardNumber
import uz.gita.m1nex.paynet.app.ui.theme.component.PayToInput
import uz.gita.m1nex.paynet.app.ui.theme.mainBgLight
import uz.gita.m1nex.presenter.screenmodel.transfer.card.TransferCardContract

class TransferCardScreen(private val cardReceiverPan: String, private val cardOwner: String, private val panKey: Int = 0) :
    Screen {
    @Composable
    override fun Content() {
        val viewModel: TransferCardContract.Model = hiltScreenModel()
        viewModel.onEventDispatcher(TransferCardContract.Intent.GetCards)
        val context = LocalContext.current
        val bottomSheetNavigator = LocalNavigator.currentOrThrow
        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is TransferCardContract.SideEffect.ShowAllCardsDialog -> {
//                    "TransferMoneySendScreen ShowAllCardsDialog".myLog()
                    bottomSheetNavigator.push(
                        CardsSheetDialog(list = sideEffect.cardList,
                        onCardClick = {
                            if (it.pan.endsWith(cardReceiverPan)) {
                                Toast.makeText(context, "Karta raqamlari bir xil", Toast.LENGTH_SHORT).show()
                            } else {
                                viewModel.onEventDispatcher(
                                    TransferCardContract.Intent.SelectCurrentCard(
                                        it
                                    )
                                )
                                bottomSheetNavigator.pop()
                            }
//                            bottomSheetNavigator.hide()
                        },
                        onAddButtonClick = {
                            viewModel.onEventDispatcher(TransferCardContract.Intent.ToAddCardScreen)
                            bottomSheetNavigator.pop()
//                            bottomSheetNavigator.hide()
                        })
                    )
                }

                is TransferCardContract.SideEffect.Toast -> {
//                    context.toToast(sideEffect.message)
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        P2PContent(
            panKey = panKey,
            cardOwner = cardOwner,
            cardReceiverPan = cardReceiverPan,
            uiState = viewModel.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
private fun P2PContent(
    panKey: Int,
    cardReceiverPan: String,
    cardOwner: String,
    uiState: State<TransferCardContract.UIState>,
    onEventDispatcher: (TransferCardContract.Intent) -> Unit
) {
    val context = LocalContext.current
    val isInputIncorrect by remember { mutableStateOf(false) }
    var paySum by remember { mutableStateOf("") }
    var cardNumberRemember by remember { mutableStateOf("0036") }
    val focusRequester = remember { FocusRequester() }
    var isTransferButtonEnabled by remember { mutableStateOf(true) }

    var currentCard by remember {
        mutableStateOf(CardData("0", "Personal", 0L, "Ali", "0036", 2029, 9, 4, true))
    }

    when (val uiStateValue = uiState.value) {
        TransferCardContract.UIState.InitState -> {
//            "TransferVerifyContract InitState".myLog()
            onEventDispatcher.invoke(TransferCardContract.Intent.GetCards)
        }

        is TransferCardContract.UIState.CurrentCard -> {
            currentCard = uiStateValue.cardData
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(color = mainBgLight)
            .padding(12.dp)
    ) {
        TopSection(text = R.string.transfer_to_card) {

        }

        Text(
            text = stringResource(R.string.from),
            modifier = Modifier.padding(top = 18.dp),
            fontSize = 18.sp,
            letterSpacing = 0.8.sp
        )

        CardP2PSendItem(cardNumber = currentCard,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            onClickItem = {
                onEventDispatcher.invoke(TransferCardContract.Intent.ClickAllCardsDialog)
            })

        Text(
            text = stringResource(R.string.to),
            modifier = Modifier.padding(top = 24.dp),
            fontSize = 18.sp,
            letterSpacing = 0.8.sp
        )

        CardP2PWithCardNumber(pankey = panKey, data = CardData(
            "", "", 0L, cardOwner, cardReceiverPan, 2028, 12, 1, true
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp), onClickItem = {})

        var isBigMoney by remember { mutableStateOf(false) }
        var isInsufficiencies by remember { mutableStateOf(false) }

        var isSearchingStateActive by remember { mutableStateOf(false) }
        var isSearchBarFocused by remember { mutableStateOf(false) }
        PayToInput(modifier = Modifier.padding(top = 16.dp),
            onClickContacts = {},
            onClickScan = {},
            onValueChange = {
                if (it.isNotEmpty()) {
                    isInsufficiencies = it.toLong() >= currentCard.amount
                    isBigMoney = it.toLong() >= 50_000_000
                }
                paySum = it
                isTransferButtonEnabled = (if (it.isNotEmpty()) {
                    it.toLong() in 1_000..50_000_000
                } else false)
            },
            focusRequester = focusRequester,
            onFocusChanged = {
                isSearchBarFocused = it.isFocused
                if (isSearchBarFocused) isSearchingStateActive = true
            })

        if (isInputIncorrect) {
            Text(
                modifier = Modifier.padding(8.dp), text = stringResource(R.string.commission_0)
            )
        }
        if (isBigMoney) {
            Text(
                color = Color.Red,
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.big_money)
            )
        }
        if (isInsufficiencies && !isBigMoney) {
            Text(
                color = Color.Red,
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.insufficient_funds)
            )
        }

        Spacer(modifier = Modifier.weight(1f))


        AppButton(modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.transfer),
            enabled = isTransferButtonEnabled && !isInsufficiencies,
            onClick = {
                if (currentCard.pan.endsWith(cardReceiverPan)) {
//                    "Karta raqamlari bir xil".toToast(context)
                    Toast.makeText(context, "Karta raqamlari bir xil", Toast.LENGTH_SHORT).show()
                } else {
//                    localStorage.tempUser = cardOwner
                    onEventDispatcher(
                        TransferCardContract.Intent.Pay(
                            senderId = currentCard.pan, receiverPan = cardReceiverPan, amount =  paySum.toInt(), pankey = panKey
                        )
                    )
                }
            })
    }
}

@Preview
@Composable
fun P2PPreview() {
    P2PContent(panKey = 1,
        cardReceiverPan = "",
        cardOwner = "",
        uiState = previewStateOf(value = TransferCardContract.UIState.InitState),
        onEventDispatcher = {})
}