package uz.gita.m1nex.paynet.app.screen.verify

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.m1nex.paynet.app.screen.password.PasswordScreen
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.password.PasswordState
import uz.gita.m1nex.presenter.screenmodel.signin.SignInContract
import uz.gita.m1nex.presenter.screenmodel.verify.VerifyContract
import javax.inject.Inject

class VerifyDirectionImpl @Inject constructor(private val navigator: AppNavigator) : VerifyContract.Direction {
    override suspend fun navigateToPassword() {
        navigator.navigateTo(PasswordScreen(PasswordState.New))
    }
    override suspend fun back() {
        navigator.back()
    }

}