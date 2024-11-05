package uz.gita.m1nex.paynet.app.screen.home.tab.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.loader.content.Loader
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LifecycleEffectOnce
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.m1nex.paynet.app.ui.theme.component.HistoryOutcomeTransactionItem
import uz.gita.m1nex.paynet.app.ui.theme.component.HistoryIncomeTransactionItem
import uz.gita.m1nex.core.data.model.Child
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.core.formatTimestampDay
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.screen.home.isShowBottomNavigation
import uz.gita.m1nex.paynet.app.ui.dialog.TransferOptionSheetContent
import uz.gita.m1nex.paynet.app.ui.dialog.TransferOptionsSheet
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite90
import uz.gita.m1nex.paynet.app.ui.theme.component.CardBalance
import uz.gita.m1nex.paynet.app.ui.theme.primaryColor
import uz.gita.m1nex.presenter.screenmodel.history.HistoryContract

object HistoryTab: Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.history)
            val icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_operations_timecircle))

            return remember(title, icon) {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }


    @OptIn(ExperimentalMaterial3Api::class, ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {

        val screenModel: HistoryContract.Model = hiltScreenModel()
        LifecycleEffectOnce {
            screenModel.onEventDispatcher(HistoryContract.Intent.GetHistory)
        }
//        screenModel.onEventDispatcher(HistoryContract.Intent.GetHistory)
        val uiState = screenModel.collectAsState()
        val bottomSheetNavigator = LocalNavigator.currentOrThrow
        val isShowDialog = remember { mutableStateOf(false) }
        val child = remember { mutableStateOf(Child(0,"",0,"","")) }
        screenModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is HistoryContract.SideEffect.OpenDialog -> {
//                    bottomSheetNavigator.push(

//                    )
                    isShowDialog.value = true
                    child.value = sideEffect.child
                }

                else -> {
                    isShowDialog.value = false
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize()){
            HistoryContent(
                uiState = uiState,
                onEventDispatcher = screenModel::onEventDispatcher,
                ls = screenModel.getCardHistory().collectAsLazyPagingItems()
            )
            if (isShowDialog.value) {
                ModalBottomSheet(containerColor = Color.White,onDismissRequest = {
                    isShowDialog.value = false
                }
                ) {
                    Box(){
                        TransferOptionSheetContent(child = child.value, onDismiss = {
                            isShowDialog.value = false
                        })
                    }
                }
            }
        }
    }
}

@Composable
private fun HistoryContent(
    uiState: State<HistoryContract.UiState>,
    onEventDispatcher: (HistoryContract.Intent) -> Unit,
    ls: LazyPagingItems<Child>
) {

    var date = ""
    var moneyRemember by remember {
        mutableStateOf("")
    }
    when (val value = uiState.value) {
        is HistoryContract.UiState.Balance -> {
            moneyRemember = value.balance
        }
        is HistoryContract.UiState.Init -> {
            onEventDispatcher.invoke(HistoryContract.Intent.GetBalance)
        }

        else -> {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Color(0xFFFAFAFA))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterStart),
                text = stringResource(id = R.string.history),
                fontSize = 32.sp,
                fontWeight = FontWeight.W600
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        CardBalance(text = moneyRemember, modifier = Modifier.padding(horizontal = 16.dp)) {

        }
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            items(ls.itemCount,
                key = ls.itemKey { it.time },
                contentType = ls.itemContentType { "contentType" }) {

                ls[it]?.let { data ->

                    val dateDay = formatTimestampDay(data.time)
                    if (date != dateDay) { // "24.03" == 24.02
                        Spacer(modifier = Modifier.size(32.dp))
                        date = dateDay

                        Text(
                            text = dateDay,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 24.dp)
                        )
                    }

                    if (data.type == "income") {
                        HistoryIncomeTransactionItem(
                            data = data
                        ) {
                            onEventDispatcher.invoke(HistoryContract.Intent.OpenPaymentMethod(data))
                        }
                    } else {
                        HistoryOutcomeTransactionItem(
                            data = data
                        ) {
                            onEventDispatcher.invoke(HistoryContract.Intent.OpenPaymentMethod(data))
                        }
                    }
                    if (it == ls.itemCount-1){
                        Spacer(modifier = Modifier.size(12.dp))
                    }
                }
            }
            when (ls.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            CircularProgressIndicator(modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .size(106.dp), color = primaryColor)
                            Spacer(modifier = Modifier.height(56.dp))
                        }

                    }
                }

                else -> {

                }
            }
        }
    }
}