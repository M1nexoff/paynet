package uz.gita.m1nex.paynet.app.screen.home

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.gita.m1nex.paynet.app.screen.home.tab.history.HistoryTab
import uz.gita.m1nex.paynet.app.screen.home.tab.main.MainTab
import uz.gita.m1nex.paynet.app.screen.home.tab.transfer.TransferTab
import uz.gita.m1nex.paynet.app.ui.theme.primaryColor

var isShowBottomNavigation: Boolean by mutableStateOf(true)

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        BottomNavigation()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigation() {

    TabNavigator(
        tab = MainTab,
        tabDisposable = {
            TabDisposable(
                navigator = it,
                tabs = listOf(MainTab, TransferTab, HistoryTab)
            )
        }
    ) {
        val localTabNavigator = LocalTabNavigator.current
        BackHandler(localTabNavigator.current != MainTab){
            localTabNavigator.current = MainTab
        }
        Scaffold(
            content = {padding->
                Box(modifier = Modifier.padding(padding)) {
                    CurrentTab()
                }
            },
            bottomBar = {
                if (isShowBottomNavigation) {
                    Card(shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)){
                        BottomNavigation(
                            modifier = Modifier
                                .height(56.dp),
                            backgroundColor = Color.White,
                            contentColor = Color.Black,
                        ) {
                            TabNavigatorItem(tab = MainTab)
                            TabNavigatorItem(tab = TransferTab)
                            TabNavigatorItem(tab = HistoryTab)
                        }
                    }
                }
            }
        )
    }
}


@Composable
fun RowScope.TabNavigatorItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        modifier = Modifier.padding(top = 8.dp),
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = {
            Text(
                text = tab.options.title,
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 12.sp
            )
        },
        icon = {
            Icon(
                painter = tab.options.icon!!,
                tint = if (tabNavigator.current == tab) primaryColor else Color.Gray,
                contentDescription = tab.options.title
            )
        }
    )
}

