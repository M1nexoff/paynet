package uz.gita.m1nex.presenter.screenmodel.home.tab.main

import android.util.Log
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.presenter.screenmodel.home.HomeContract
import uz.gita.m1nex.usecase.home.HomeUseCase
import javax.inject.Inject
import kotlin.math.log

class MainScreenModelImpl @Inject constructor(
    private val homeDirection: HomeContract.Direction,
    private val homeUseCase: HomeUseCase
) : MainContract.Model {
    init {
        Log.d("TTT", "MainScreenInit: init")
        homeUseCase.getBasicUserInfo().onSuccess {
            intent { reduce { MainContract.UiState.BasicInfo(it.name,it.balance) } }
            Log.d("TTT", "MainScreenInit: balance ${it.balance} name ${it.name} ")
        }
    }
    override fun onEventDispatcher(intent: MainContract.Intent) = intent {

    }

    override val container: Container<MainContract.UiState, MainContract.SideEffect> = container(getDefault())

    private fun getDefault() = MainContract.UiState.Default
}