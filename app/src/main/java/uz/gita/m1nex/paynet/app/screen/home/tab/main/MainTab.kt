package uz.gita.m1nex.paynet.app.screen.home.tab.main

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LifecycleEffectOnce
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.core.previewStateOf
import uz.gita.m1nex.core.toFormat
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.screen.home.isShowBottomNavigation
import uz.gita.m1nex.paynet.app.screen.home.tab.transfer.TransferTab
import uz.gita.m1nex.paynet.app.ui.dialog.AddCardDialog
import uz.gita.m1nex.paynet.app.ui.dialog.OptionBottomSheetContent
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundLight
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.app.ui.theme.circleStartColorGreen
import uz.gita.m1nex.paynet.app.ui.theme.component.Card
import uz.gita.m1nex.paynet.app.ui.theme.component.CashBack
import uz.gita.m1nex.paynet.app.ui.theme.component.MyCardsEmpty
import uz.gita.m1nex.paynet.app.ui.theme.component.MyCardsMoreCards
import uz.gita.m1nex.paynet.app.ui.theme.component.MyCardsOneCard
import uz.gita.m1nex.paynet.app.ui.theme.component.MyCardsTwoCards
import uz.gita.m1nex.paynet.app.ui.theme.securityCardEndColor
import uz.gita.m1nex.paynet.app.ui.theme.securityCardStartColor
import uz.gita.m1nex.paynet.app.ui.theme.textColor
import uz.gita.m1nex.paynet.app.ui.theme.white
import uz.gita.m1nex.presenter.screenmodel.home.tab.main.MainContract

object MainTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.main)
            val icon =
                rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_home))

            return remember(title, icon) {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @OptIn(ExperimentalVoyagerApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalNavigator.currentOrThrow
        val lifecycleOwner = LocalLifecycleOwner.current
        val context = LocalContext.current
        val viewModel: MainContract.Model = hiltScreenModel()
        LifecycleEffectOnce {
            viewModel.onInit()
        }
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_START -> {
                        viewModel.onEventDispatcher(MainContract.Intent.GetBasicInfo)
                    }

                    else -> {

                    }
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        val bottomNavigator = LocalNavigator.current
        val isShowDialog = remember { mutableStateOf(false) }
        val list = remember { mutableListOf<CardData>() }
        Box(modifier = Modifier.fillMaxSize(1f)) {
            MainScreenContent(viewModel.collectAsState(), viewModel::onEventDispatcher)
            if (isShowDialog.value) {
                ModalBottomSheet(containerColor = Color.White, onDismissRequest = {
                    isShowDialog.value = false
                }
                ) {
                    Box{
                        OptionBottomSheetContent(
                            list = list,
                            onAddButtonClick = {
                                viewModel.onEventDispatcher(MainContract.Intent.OpenAddScreen)
                            },
                            onCardClick = {
                                viewModel.onEventDispatcher(
                                    MainContract.Intent.OpenPaynetCardScreen(
                                        it
                                    )
                                )
                                isShowDialog.value = false
                            }, close = {
                                isShowDialog.value = false
                            }
                        )
                    }
                }
            }
            viewModel.collectSideEffect {
                when (it) {
                    MainContract.SideEffect.AddCardDialog -> {
                        AddCardDialog {
                            viewModel.onEventDispatcher(MainContract.Intent.OpenAddScreen)
                        }
                        isShowDialog.value = false
                    }

                    is MainContract.SideEffect.ShowAllCardsDialog -> {
                        list.clear()
                        list.addAll(it.cardList)
                        isShowDialog.value = true
                    }
                }
            }
        }
    }
}


//@Composable
//private fun MainScreenContent2(uiState: State<MainContract.UiState> = mutableStateOf(MainContract.UiState.Default), onEventDispatcher: (MainContract.Intent) -> Unit = {}) {
//    val name = remember { mutableStateOf("") }
//    val money by remember { mutableStateOf(0) }
////    when (uiState.value) {
////        MainContract.UiState.Default -> {
////
////        }
////        is MainContract.UiState.BasicInfo -> {
////            name.value = (uiState.value as MainContract.UiState.BasicInfo).userName
//////            money.value = (uiState.value as MainContract.UiState.BasicInfo).balance
////        }
////    }
//    PaynetOfficialTheme {
//        Column(
//            Modifier
//                .background(Color(0xFFF7F7F7))
//                .fillMaxSize()) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(12.dp)
//                    .clickable { }
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(40.dp)
//                        .clip(CircleShape)
//                        .background(MaterialTheme.colorScheme.primary)
//                )
//
//                Spacer(modifier = Modifier.width(8.dp))
//
//                Column {
//                    Text(
//                        text = name.value,
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black
//                    )
//                }
//                Box(modifier = Modifier.weight(1f)){
//                    Row(
//                        modifier = Modifier
//                            .align(Alignment.CenterEnd)
//                            .padding(16.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//
//                        Box(modifier = Modifier.clickable { }) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_notification),
//                                contentDescription = "Notifications",
//                                tint = Color.Gray,
//                                modifier = Modifier
//                                    .padding(end = 6.dp)
//                                    .size(28.dp)
//                            )
//                            if (3 > 0) {
//                                Badge(
//                                    containerColor = Color.Red,
//                                    contentColor = Color.White,
//                                    modifier = Modifier
//                                        .size(22.dp)
//                                        .border(1.dp, color = Color.White, shape = CircleShape)
//                                        .align(Alignment.TopEnd)
//                                        .clip(CircleShape)
//                                ) {
//                                    Text(
//                                        text = "3",
//                                    )
//                                }
//                            }
//                        }
//                    }
//
//                }
//            }
//
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//                item {
//                    var isBalanceVisible = remember { mutableStateOf(true) }
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
//                            Text(
//                                text = "Mening pulim",
//                                style = MaterialTheme.typography.bodyLarge,
//                                color = Color.Gray
//                            )
//                            Row() {
//                                Text(
//                                    text = if (isBalanceVisible.value) money.toString().toFormat(3) else "•••••",
//                                    fontSize = 36.sp,
//                                    style = MaterialTheme.typography.titleLarge,
//                                    color = Color.Black
//                                )
//                                Text(
//                                    text = "so'm",
//                                    style = MaterialTheme.typography.titleLarge,
//                                    color = Color.LightGray,
//                                    modifier = Modifier
//                                        .padding(start = 4.dp, bottom = 4.dp)
//                                        .align(Alignment.Bottom)
//                                )
//                                Box(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .height(40.dp)
//                                ) {
//                                    Icon(
//                                        painter = painterResource(id = if (isBalanceVisible.value) R.drawable.ic_action_eye_open else R.drawable.ic_action_eye_close),
//                                        contentDescription = "Toggle Visibility",
//                                        modifier = Modifier
//                                            .align(Alignment.BottomEnd)
//                                            .padding(end = 16.dp)
//                                            .size(28.dp)
//                                            .clickable {
//                                                isBalanceVisible.value = !isBalanceVisible.value
//
//                                            },
//                                        tint = Color.Gray
//                                    )
//                                }
//                            }
//                        }
//
//
//                    }
//                }
//                item {
//                    PaynetCardUI()
//                }
//            }
//        }
//    }
//
//}

@Composable
fun PaynetCardUI(pan: String = "0000", balance: String = "0") {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .shadow(2.dp, RoundedCornerShape(12.dp), ambientColor = Color.LightGray)
            .background(Color(0xFFFAFAFA), RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Paynet karta",
            style = MaterialTheme.typography.bodyLarge,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_paynet_logo_dark),
                    contentDescription = "Paynet Logo",
                    modifier = Modifier
                        .height(52.dp)
                        .width(86.dp)
                        .padding(end = 8.dp)
                        .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
                        .padding(start = 8.dp, end = 8.dp),
                    tint = Color.White
                )

                Column {
                    Text(
                        text = "${stringResource(R.string.paynet_card)} • ${pan}",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge,

                        )
                    Text(
                        text = "${
                            buildAnnotatedString {
                                append(balance)
                                addStyle(SpanStyle(Color.Black), 0, balance.length)
                            }
                        } so'm",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }

            Text(
                text = "Bu nima?",
                color = Color(0xFF00B14F),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable {

                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            PaynetActionButton(
                id = R.drawable.ic_add,
                text = "To'ldirish",
                modifier = Modifier.weight(1f)
            )

            PaynetActionButton(
                id = R.drawable.transfer,
                text = "O'tkazish",
                modifier = Modifier.weight(1f)
            )

            PaynetActionButton(
                id = R.drawable.wallet,
                text = "To'lash",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun PaynetActionButton(id: Int, text: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .height(86.dp)
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .shadow(2.dp, RoundedCornerShape(16.dp))
            .then(modifier)
            .background(Color.White, RoundedCornerShape(16.dp))

    ) {
        Box(
            modifier = Modifier
                .weight(1.5f)
        ) {
            Icon(
                painter = painterResource(id),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = text,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(36.dp)
            )
        }

        Text(
            text = text,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    PaynetOfficialTheme {
        Scaffold {
            Column {
                MainScreenContent(
                    previewStateOf(MainContract.UiState.Default),
                    {}
                )
            }
        }
    }
}


@Composable
fun MainScreenContent(
    uiState: State<MainContract.UiState>,
    onEventDispatcher: (MainContract.Intent) -> Unit,
) {
    val context = LocalContext.current
    var moneyVisibleRemember by remember {
        mutableStateOf(false)
    }
    var cards by remember {
        mutableStateOf(listOf<CardData>())
    }
    val name = rememberSaveable { mutableStateOf("") }
    val money = rememberSaveable { mutableStateOf(0) }
    var cardRemember by remember {
        mutableIntStateOf(1)
    }
    var isRefreshing by remember { mutableStateOf(false) }
    when (uiState.value) {
        is MainContract.UiState.BasicState -> {
            isRefreshing = false
            name.value = (uiState.value as MainContract.UiState.BasicState).phone
            money.value = (uiState.value as MainContract.UiState.BasicState).balance
        }

        MainContract.UiState.Default -> {

        }

        is MainContract.UiState.CardsState -> {
            cards = (uiState.value as MainContract.UiState.CardsState).list
        }
    }
    var items by remember { mutableStateOf(listOf("Item 1", "Item 2", "Item 3")) }
//    val bottomTabNavigator = LocalTabNavigator.current
    // SwipeRefresh layout
    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing), onRefresh = {
        // Simulate a network request
        isRefreshing = true
        onEventDispatcher.invoke(MainContract.Intent.GetBasicInfo)
//            LaunchedEffect(Unit) {
//                delay(2000) // Simulate network delay
//                items = items.shuffled() // Update the list
//                isRefreshing = false
//            }
    }) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (isRefreshing) {
                onEventDispatcher.invoke(MainContract.Intent.GetBasicInfo)
            }

            var amount by remember {
                mutableStateOf("")
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(BackgroundLight)
            ) {
                Box(modifier = Modifier
                    .padding(start = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .size(28.dp)
                    .clickable {
                        onEventDispatcher.invoke(MainContract.Intent.OpenProfile)
                    }
                    .background(circleStartColorGreen)
                    .align(Alignment.CenterVertically)


                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = name.value,
                    Modifier
                        .align(Alignment.CenterVertically)
                        .clip(CircleShape)
                        .clickable { onEventDispatcher.invoke(MainContract.Intent.OpenProfile) },
                    style = MaterialTheme.typography.labelLarge,
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_chevron_down_x24),
                    contentDescription = "",
                    Modifier
                        .clip(CircleShape)
                        .size(20.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            onEventDispatcher.invoke(MainContract.Intent.OpenProfile)
                        }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .padding(end = 16.dp)
                ) {
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable {
                                    val intent =
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("https://chat.paynet.uz/")
                                        )
                                    startActivity(context, intent, Bundle())

                                },
                            painter = painterResource(id = R.drawable.ic_operation_support),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable {
                                    onEventDispatcher.invoke(MainContract.Intent.OpenNotifications)
                                },
                            painter = painterResource(id = R.drawable.ic_operations_bell_new),
                            contentDescription = ""
                        )
                    }
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(BackgroundLight)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.my_money),
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        color = textColor
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
//                        999491117
//                        qwerty
                        Text(
                            text = if (moneyVisibleRemember) money.value.toString().toFormat(3) else "•••••",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.som),
                            style = MaterialTheme.typography.titleLarge,
                            color = textColor
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                        ) {
                            IconButton(modifier = Modifier
                                .clip(CircleShape)
                                .align(Alignment.CenterEnd)
                                .padding(end = 16.dp), onClick = {
                                moneyVisibleRemember = !moneyVisibleRemember
                            }) {
                                Icon(
                                    painter = painterResource(id = if (moneyVisibleRemember) R.drawable.ic_action_eye_open else R.drawable.ic_action_eye_close),
                                    contentDescription = "eye",
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                            text = stringResource(id = R.string.paynet_card),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                modifier = Modifier
                                    .padding(end = 12.dp, top = 12.dp)
                                    .align(Alignment.CenterEnd)
                                    .clickable {
                                        onEventDispatcher.invoke(
                                            MainContract.Intent.OpenWhatIsThisScreen
                                        )
                                    },
                                text = stringResource(id = R.string.what_is_it),
                                style = MaterialTheme.typography.titleMedium,
                                color = circleStartColorGreen
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 9.dp, top = 4.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .width(72.dp)
                                .height(56.dp),
                            painter = painterResource(id = R.drawable.paynet),
                            contentDescription = ""
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                                .padding(start = 4.dp)
                        ) {

                            Text(
                                text = stringResource(id = R.string.paynet_card),
                                fontSize = 16.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                color = textColor
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = if (moneyVisibleRemember) money.value.toString()
                                        .toFormat(3) else "•••••",
                                    style = MaterialTheme.typography.titleMedium,
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(id = R.string.som),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = textColor
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 6.dp, end = 6.dp, bottom = 8.dp, top = 4.dp)
                    ) {
                        val tabNavigator = LocalTabNavigator.current
                        Card(
                            modifier = Modifier
                                .height(80.dp)
                                .padding(4.dp)
                                .weight(1f)
                                .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .background(white)
                                .clickable {
                                    tabNavigator.current = TransferTab
                                },
                            icon = R.drawable.ic_action_plus,
                            text = R.string.fill
                        )
                        Card(
                            modifier = Modifier
                                .height(80.dp)
                                .padding(4.dp)
                                .weight(1f)
                                .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .background(white)
                                .clickable {
//                                    bottomTabNavigator.current = TransferTab
                                }, icon = R.drawable.ic_action_transfers, text = R.string.transfer
                        )
                        Card(
                            modifier = Modifier
                                .height(80.dp)
                                .padding(4.dp)
                                .weight(1f)
                                .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .background(white)
                                .clickable {

                                },
                            icon = R.drawable.ic_operations_wallet,
                            text = R.string.pay
                        )
                    }
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 9.dp, top = 4.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .width(72.dp)
                                .height(82.dp),
                            painter = painterResource(id = R.drawable.paynetjon_promotion),
                            contentDescription = ""
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterVertically)
                                .padding(start = 4.dp, top = 4.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.your_level) + stringResource(id = R.string.starter),
                                style = MaterialTheme.typography.bodyLarge,
                                color = textColor
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "0",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(id = R.string.coin),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = textColor,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp, end = 16.dp, bottom = 4.dp)
                                    .background(BackgroundLight)
                                    .clip(
                                        CircleShape
                                    ),
                                progress = 0f,
                            )
                            Text(
                                text = "100 " + stringResource(id = R.string.left_after_next_level),
                                style = MaterialTheme.typography.bodySmall,
                                color = textColor
                            )

                        }

                    }
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, bottom = 10.dp, top = 4.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.exchange),
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.Black
                        )

                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .shadow(elevation = 2.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(securityCardStartColor, securityCardEndColor)
                            )
                        )
                ) {
                    Row(Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                modifier = Modifier.padding(top = 12.dp, start = 12.dp),
                                text = stringResource(id = R.string.paynet_security),
                                color = Color.White,
                                style = MaterialTheme.typography.labelLarge,
                            )
                            Text(
                                text = stringResource(id = R.string.upgrade_security),
                                modifier = Modifier.padding(start = 12.dp),
                                color = Color.White,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = stringResource(id = R.string.want_to),
                                modifier = Modifier.padding(start = 12.dp),
                                color = Color.White,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Button(
                                modifier = Modifier
                                    .padding(
                                        start = 12.dp, bottom = 12.dp, top = 8.dp
                                    )
                                    .height(36.dp),
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(white),

                                ) {
                                Text(
                                    text = stringResource(id = R.string.verify_identity),
                                    color = Color.Black,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(0.7f)
                                .align(Alignment.CenterVertically)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.securety_identification),
                                contentDescription = "security",
                                modifier = Modifier.align(Alignment.Center)
                            )

                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                ) {
                    Log.d("TTT", cards.toString())
                    if (cards.isEmpty()) {
                        MyCardsEmpty(
                            modifier = Modifier
                                .padding(end = 6.dp)
                                .weight(1f),
                            onClickAddCard = {
                                onEventDispatcher.invoke(MainContract.Intent.OpenAddScreen)
                            }
                        )
                    } else if (cards.size == 1) {
                        MyCardsOneCard(
                            modifier = Modifier
                                .padding(end = 6.dp)
                                .weight(1f),
                            onClickAddCard = {
                                onEventDispatcher.invoke(MainContract.Intent.OpenAddScreen)
                            },
                            onClickCard = {
                                onEventDispatcher.invoke(
                                    MainContract.Intent.OpenPaynetCardScreen(it)
                                )
                            },
                            card = cards[0]
                        )
                    } else if (cards.size == 2) {
                        MyCardsTwoCards(
                            modifier = Modifier
                                .padding(end = 6.dp)
                                .weight(1f),
                            onClickAddCard = { onEventDispatcher.invoke(MainContract.Intent.OpenAddScreen) },
                            onClickFrontCard = {
                                onEventDispatcher.invoke(MainContract.Intent.OpenPaynetCardScreen(it))
                            },
                            onClickBackCard = {
                                onEventDispatcher.invoke(
                                    MainContract.Intent.OpenPaynetCardScreen(
                                        it
                                    )
                                )
                            },
                            frontCard = cards[0],
                            backCard = cards[1]
                        )
                    } else {
                        MyCardsMoreCards(
                            modifier = Modifier
                                .padding(end = 6.dp)
                                .weight(1f),
                            onAllCardClick = {
                                onEventDispatcher.invoke(
                                    MainContract.Intent.OpenAllCardsScreen(
                                        cards
                                    )
                                )
                            },
                            onClickAddCard = { onEventDispatcher.invoke(MainContract.Intent.OpenAddScreen) },
                            onClickFrontCard = {
                                onEventDispatcher.invoke(
                                    MainContract.Intent.OpenPaynetCardScreen(it)
                                )
                            },
                            onClickBackCard = {
                                onEventDispatcher.invoke(
                                    MainContract.Intent.OpenPaynetCardScreen(it)
                                )
                            },
                            cards = cards
                        )
                    }

                    CashBack(
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .weight(1f),
                        isVisibleMoney = true,
                        money = money.value.toString()
                    )
                }
                val a = 1000000000000000000L


//                CardAvia()
//                CardMyHouse()
//                CardMIB()


                Spacer(modifier = Modifier.padding(16.dp))
            }

        }
    }
}


fun gcd(x: Long, y: Long): Long = if (y == 0L) x else gcd(y, x % y)
fun lcm(x: Long, y: Long): Long = (x * y) / gcd(x, y)

fun countValidNumbers(limit: Long, a: Int, b: Int, c: Int): Long {
    val ab = lcm(a.toLong(), b.toLong())
    val ac = lcm(a.toLong(), c.toLong())
    val bc = lcm(b.toLong(), c.toLong())
    val abc = lcm(ab, c.toLong())

    return (limit / ab + limit / ac + limit / bc - 3 * (limit / abc))
}

fun findNthNumber(a: Int, b: Int, c: Int, n: Long): Long {
    val limit = 1000000000000000000L
    var low = 1L
    var high = limit

    while (low < high) {
        val mid = (low + high) / 2
        val count = countValidNumbers(mid, a, b, c)

        if (count >= n) {
            high = mid
        } else {
            low = mid + 1
        }
    }

    return if (low <= limit) low else -1
}

fun main() {

    try {
        val (a, b, c) = readln().split(" ").map { it.toInt() }
        val n = readln().toLong()
        val result = findNthNumber(a, b, c, n)
        println(result)
    } catch (e: Exception) {
        val n = readln().toLong()

        println(-1)
    }

}
