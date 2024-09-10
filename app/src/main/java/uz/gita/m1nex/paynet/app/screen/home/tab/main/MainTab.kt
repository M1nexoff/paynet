package uz.gita.m1nex.paynet.app.screen.home.tab.main

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.app.ui.theme.main
import uz.gita.m1nex.presenter.screenmodel.home.HomeContract
import uz.gita.m1nex.presenter.screenmodel.home.HomeScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.home.tab.main.MainContract

object MainTab: Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.main)
            val icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_home))

            return remember(title, icon) {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel: MainContract.Model = hiltScreenModel()
        viewModel.collectSideEffect {
            when(it){
                is MainContract.SideEffect.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        MainScreenContent(viewModel.collectAsState(), viewModel::onEventDispatcher)
    }
}


@Composable
private fun MainScreenContent(uiState: State<MainContract.UiState> = mutableStateOf(MainContract.UiState.Default), onEventDispatcher: (MainContract.Intent) -> Unit = {}) {
    val name = remember { mutableStateOf("") }
    val money = remember { mutableStateOf(0) }
    when (uiState.value) {
        MainContract.UiState.Default -> {

        }
        is MainContract.UiState.BasicInfo -> {
            name.value = (uiState.value as MainContract.UiState.BasicInfo).userName
            money.value = (uiState.value as MainContract.UiState.BasicInfo).balance
        }
    }
    PaynetOfficialTheme {
        Column(
            Modifier
                .background(Color(0xFFF7F7F7))
                .fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .clickable { }
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = name.value,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Box(modifier = Modifier.weight(1f)){
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(modifier = Modifier.clickable { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_notification),
                                contentDescription = "Notifications",
                                tint = Color.Gray,
                                modifier = Modifier
                                    .padding(end = 6.dp)
                                    .size(28.dp)
                            )
                            if (3 > 0) {
                                Badge(
                                    containerColor = Color.Red,
                                    contentColor = Color.White,
                                    modifier = Modifier
                                        .size(22.dp)
                                        .border(1.dp, color = Color.White, shape = CircleShape)
                                        .align(Alignment.TopEnd)
                                        .clip(CircleShape)
                                ) {
                                    Text(
                                        text = "3",
                                    )
                                }
                            }
                        }
                    }

                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    var isBalanceVisible = remember { mutableStateOf(true) }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                            Text(
                                text = "Mening pulim",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Gray
                            )
                            Row() {
                                Text(
                                    text = if (isBalanceVisible.value) money.value.toString() else "•••••",
                                    fontSize = 36.sp,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.Black
                                )
                                Text(
                                    text = "so'm",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.LightGray,
                                    modifier = Modifier
                                        .padding(start = 4.dp, bottom = 4.dp)
                                        .align(Alignment.Bottom)
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = if (isBalanceVisible.value) R.drawable.ic_show_password else R.drawable.ic_hide_password),
                                        contentDescription = "Toggle Visibility",
                                        modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .padding(end = 16.dp)
                                            .size(28.dp)
                                            .clickable {
                                                isBalanceVisible.value = !isBalanceVisible.value

                                            },
                                        tint = Color.Gray
                                    )
                                }
                            }
                        }


                    }
                }
                item {
                    PaynetCardUI()
                }
            }
        }
    }

}

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
                MainScreenContent()
            }
        }
    }
}
