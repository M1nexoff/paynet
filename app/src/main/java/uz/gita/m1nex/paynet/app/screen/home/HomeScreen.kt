package uz.gita.m1nex.paynet.app.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.gita.m1nex.paynet.app.screen.home.tab.main.MainTab
import uz.gita.m1nex.paynet.app.screen.home.tab.transfer.TransferTab

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
                tabs = listOf(MainTab, TransferTab)
            )
        }
    ) {
        Scaffold(
            content = {
                CurrentTab()
//                Surface(modifier = Modifier.fillMaxSize()) {
//                    Column(modifier = Modifier.fillMaxSize()) {
//                        Box(modifier = Modifier.weight(1f)) {
//                            CurrentTab()
//                        }
//
//                        Button(onClick = { onEventDispatcher.invoke(MainContract.Intent.LogOut) }) {
//                            Text(text = "Main screen button")
//                        }
//                    }
//                }
            },
            bottomBar = {
                BottomNavigation{
                    TabNavigatorItem(tab = MainTab)
                    TabNavigatorItem(tab = TransferTab)
                }
            }
        )
    }
}


@Composable
fun RowScope.TabNavigatorItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = {
            Text(
                text = tab.options.title,
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 12.sp
            )
        },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}

