package uz.gita.m1nex.paynet.app.screen.home

import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.home.HomeContract
import javax.inject.Inject

class HomeDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): HomeContract.Direction {
    override suspend fun back() {

    }
}