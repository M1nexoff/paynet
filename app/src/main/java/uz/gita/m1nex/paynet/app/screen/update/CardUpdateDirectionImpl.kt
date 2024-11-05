package uz.gita.m1nex.paynet.app.screen.update

import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.update.CardUpdateContract
import javax.inject.Inject

class CardUpdateDirectionImpl @Inject constructor(
    val appNavigator: AppNavigator
): CardUpdateContract.Direction {
    override suspend fun back() {
        appNavigator.back()
    }
}