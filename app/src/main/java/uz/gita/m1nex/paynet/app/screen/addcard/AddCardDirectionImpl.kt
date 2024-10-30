package uz.gita.m1nex.paynet.app.screen.addcard

import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.addcard.AddCardContract
import javax.inject.Inject

class AddCardDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : AddCardContract.Direction {
    override suspend fun backToMainScreen() {
        appNavigator.back()
    }
}