package uz.gita.m1nex.presenter.screenmodel.home.tab.main

import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import uz.gita.m1nex.presenter.screenmodel.home.HomeContract
import uz.gita.m1nex.usecase.home.HomeUseCase
import javax.inject.Inject

class MainScreenModelImpl @Inject constructor(
    private val homeDirection: HomeContract.Direction,
    private val homeUseCase: HomeUseCase
) : MainContract.Model {
    override fun onEventDispatcher(intent: MainContract.Intent) = intent {

    }

    override val container: Container<MainContract.UiState, MainContract.SideEffect> = container(getDefault())

    private fun getDefault() = MainContract.UiState.Default
}