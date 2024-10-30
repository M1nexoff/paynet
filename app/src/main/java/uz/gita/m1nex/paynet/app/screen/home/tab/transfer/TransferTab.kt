package uz.gita.m1nex.paynet.app.screen.home.tab.transfer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.m1nex.paynet.app.ui.theme.component.SearchBar
import uz.gita.m1nex.paynet.app.ui.theme.component.TopBarTransfer
import uz.gita.m1nex.paynet.app.ui.theme.component.ReceiverCardItemComponent
import uz.gita.m1nex.core.data.model.transfer.LastTransferData
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.component.DefaultState
import uz.gita.m1nex.paynet.app.ui.theme.component.TextBoldBlack
import uz.gita.m1nex.paynet.app.ui.theme.grayIcon
import uz.gita.m1nex.presenter.screenmodel.transfer.TransferContract

object TransferTab : Tab {
    override val options: TabOptions
        @Composable get() {
            val title = stringResource(id = R.string.transfer)
            val icon =
                rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_action_transfers))

            return remember {
                TabOptions(
                    index = 0u, title = title, icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val screenModel: TransferContract.Model = hiltScreenModel()
        screenModel.onEventDispatchers(TransferContract.Intent.GetTemplates)
        screenModel.onEventDispatchers(TransferContract.Intent.GetLastTransfers)
        val uiState = screenModel.collectAsState()
        TransactionsScreenContent(uiState, screenModel::onEventDispatchers)
    }
}

@Composable
private fun TransactionsScreenContent(
    uiState: State<TransferContract.UIState>, onEventDispatchers: (TransferContract.Intent) -> Unit
) {
//    val localBottomSheetNavigator = LocalBottomSheetNavigator.current
    var searchText by remember { mutableStateOf("") }
    var isSearchingStateActive by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var isSearchBarFocused by remember { mutableStateOf(false) }
    var listCardRemember by remember {
        mutableStateOf(listOf<LastTransferData>())
    }
//    var templates by remember {
//        mutableStateOf(listOf<TemplateTable>())
//    }

    Scaffold(topBar = {
        TopBarTransfer(modifier = Modifier.fillMaxWidth(),
            visible = isSearchingStateActive,
            onClick = {
                isSearchingStateActive = false
                searchText = ""
                focusManager.clearFocus(true)
            })
    }) {
        Column(
            modifier = Modifier/*.verticalScroll(rememberScrollState())*/.padding(it),
        ) {
            SearchBar(modifier = Modifier.padding(horizontal = 12.dp),
                onClickContacts = {},
                onClickScan = {},
                onValueChange = {
                    if (it.length == 16) {
                        searchText = it
                        onEventDispatchers.invoke(TransferContract.Intent.GetUserData(searchText))
                    } else {
                        searchText = it
                    }

                },
                focusRequester = focusRequester,
                onFocusChanged = {
                    isSearchBarFocused = it.isFocused
                    if (isSearchBarFocused) isSearchingStateActive = true
                })



            when (val uiStateValue = uiState.value) {
                is TransferContract.UIState.SetCardData -> {
                    val data = uiStateValue.it
                    if(searchText.length == 16) {
                        OnSearchState(
                            listOf(LastTransferData(0L, data, searchText)), true
                        ) { intent ->
                            onEventDispatchers.invoke(intent)
                        }

                    }
                }

//                is TransferContract.UIState.SetTemplates -> {
//                    "136 row SetTemplates -> ${uiStateValue.list.joinToString()}"
////                    templates = uiStateValue.list
//                }

                is TransferContract.UIState.SetLastTransfers -> {
                    "140 row SetLastTransfers -> ${uiStateValue.list.joinToString()}"
                    listCardRemember = uiStateValue.list
                }

                else -> {}
            }

            if (isSearchingStateActive) {
                Row(Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
                    TextBoldBlack(text = stringResource(id = R.string.last),)
                    Spacer(modifier = Modifier.weight(1f))
                    TextBoldBlack(text = stringResource(id = R.string.clear),
                        color = grayIcon,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                focusManager.clearFocus(true)
//                                localBottomSheetNavigator.show(
//                                    DeleteLastItemsDialog(clickClear = {
//                                    }, clickCancel = {
//                                        localBottomSheetNavigator.hide()
//                                    })
//                                )
//                                searchText
                            })
                    Image(
                        painter = painterResource(id = R.drawable.ic_operations_delete),
                        contentDescription = null
                    )
                }
                OnSearchState(listCardRemember, true) { intent ->
                    onEventDispatchers.invoke(intent)
                }
            } else {
                when (val uiStateValue = uiState.value) {
                    is TransferContract.UIState.SetLastTransfers -> {
                        "173 row SetLastTransfers -> ${uiStateValue.list.joinToString()}"
                        listCardRemember = uiStateValue.list
                    }

//                    is TransferContract.UIState.SetTemplates -> {
//                        "178 row SetTemplates -> ${uiStateValue.list.joinToString()}"
////                        templates = uiStateValue.list
//                    }

                    else -> {

                    }
                }
                DefaultState(
                    list = listCardRemember,
                    onClickLastPayedCard = {
                        onEventDispatchers.invoke(
                            TransferContract.Intent.ToP2PScreen(
                                it.pan, it.owner
                            )
                        )
                    },
                    onClickTemplate = { templateTable ->
                        onEventDispatchers.invoke(
                            TransferContract.Intent.ToP2PScreen(
                                templateTable.pan,
                                templateTable.owner
                            )
                        )
                    },
                    onClickAddTemplate = {
                        onEventDispatchers.invoke(TransferContract.Intent.ToAddTemplateScreen)
                    })

            }

        }
    }
}
//
//@Preview(showBackground = true, device = "id:pixel_7_pro")
//@Composable
//fun TransactionsScreenPreview() {
//    MobileBankingTheme {
//        TransactionsScreenContent(previewStateOf(value = TransferContract.UIState.InitState), {})
//    }
//}

@Composable
fun OnSearchState(
    list: List<LastTransferData>,
    processType: Boolean,
    onEventDispatchers: (TransferContract.Intent) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        if (processType) {
            items(items = list) {
                ReceiverCardItemComponent(cardPan = it.pan, cardOwnerName = it.owner) {
                    onEventDispatchers.invoke(TransferContract.Intent.ToP2PScreen(it.pan, it.owner))
                }
            }
        } else {
            items(items = list) {
                ReceiverCardItemComponent(cardPan = it.pan, cardOwnerName = it.owner) {
                    onEventDispatchers.invoke(TransferContract.Intent.ToP2PScreen(it.pan, it.owner))
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun PinScreenPreview() {
//    MobileBankingTheme {
//        TransferContent()
//    }
//}


