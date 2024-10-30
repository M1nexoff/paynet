package uz.gita.m1nex.presenter.screenmodel.home

import kotlinx.coroutines.Job
import org.orbitmvi.orbit.Container
import javax.inject.Inject

internal class HomeScreenModelImpl @Inject constructor(
    private val direction: HomeContract.Direction
) : HomeContract.Model{
    init {

    }
    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when(intent){
            else -> {}
        }
    }

    override val container: Container<HomeContract.UiState, HomeContract.SideEffect> = container(getDefault())

    private fun getDefault() = HomeContract.UiState.DefaultState
}