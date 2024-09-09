package uz.gita.m1nex.paynet.app.screen.password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.m1nex.paynet.app.screen.home.HomeScreen
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.password.PasswordContract
import javax.inject.Inject

class PasswordDirectionImpl @Inject constructor(
    val navigator: AppNavigator
): PasswordContract.Direction {
    override suspend fun navigateToHomeScreen() {
        navigator.replaceAll(HomeScreen())
    }


    override suspend fun back() {
        navigator.back()
    }
}
