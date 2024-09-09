package uz.gita.m1nex.presenter.screenmodel.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.m1nex.core.data.LaunchData
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.usecase.splash.SplashUseCase
import javax.inject.Inject


class SplashScreenModelImpl @Inject constructor(
    private val direction: SplashContract.Direction,
    private val splashUseCase: SplashUseCase
) : SplashContract.Model{

    init {
        splashUseCase.checkLaunchMode()
            .onSuccess {
                Log.d("TTT","LaunchData=$it")
                delay(1500)
                when(it){
                    LaunchData.FIRST_TIME -> direction.navigateToSignUp()
                    LaunchData.SIGNED -> direction.navigateToHome()
                    LaunchData.UNSIGNED -> direction.navigateToLogin()
                }
//                direction.navigateToSignUp()
            }
            .launchIn(screenModelScope)
    }
}