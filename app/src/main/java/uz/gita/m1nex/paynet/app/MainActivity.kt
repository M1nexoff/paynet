package uz.gita.m1nex.paynet.app

import android.content.res.Resources
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.m1nex.paynet.app.screen.splash.SplashScreen
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.paynet.navigation.AppNavigatorHandler
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    @Inject
    lateinit var appNavigatorHandler: AppNavigatorHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaynetOfficialTheme {
                Navigator(SplashScreen()) { navigator ->
                    LaunchedEffect(key1 = navigator) {
                        appNavigatorHandler.navigation
                            .collect {
                                it(navigator)
                            }
                    }
                    CurrentScreen()
                }
            }
        }
    }

    override fun getResources(): Resources {
        val res = super.getResources()
        val config = res.configuration
        if (config.fontScale != 1f) {
            config.fontScale = 1f
            res.updateConfiguration(config, res.displayMetrics)
        }
        return res
    }

}