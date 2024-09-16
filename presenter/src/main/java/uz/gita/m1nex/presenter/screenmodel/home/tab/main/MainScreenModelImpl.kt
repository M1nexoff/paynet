package uz.gita.m1nex.presenter.screenmodel.home.tab.main

import android.util.Log
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.launchIn
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.m1nex.core.getText
import uz.gita.m1nex.core.onFailure
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.presenter.screenmodel.home.HomeContract
import uz.gita.m1nex.usecase.home.HomeUseCase
import javax.inject.Inject
import kotlin.math.log

internal class MainScreenModelImpl @Inject constructor(
    private val homeDirection: HomeContract.Direction,
    private val homeUseCase: HomeUseCase
) : MainContract.Model {
    init {
        Log.d("TTT", "MainScreenInit: init")
        homeUseCase.getBasicUserInfo().onSuccess {
            intent { reduce { MainContract.UiState.BasicInfo(it.firstName) } }
            Log.d("TTT", "MainScreenInit: firstName ${it.firstName}")
        }.onFailure {
            Log.d("TTT", "MainScreenInit: firstName ${it.toString()}")
        }.launchIn(screenModelScope)
    }
    override fun onEventDispatcher(intent: MainContract.Intent) = intent {

    }

    override val container: Container<MainContract.UiState, MainContract.SideEffect> = container(getDefault())

    private fun getDefault() = MainContract.UiState.Default
}