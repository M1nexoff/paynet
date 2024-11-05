package uz.gita.m1nex.paynet.app.screen.user

import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.user.UserDataContract
import javax.inject.Inject

class UserDataDirectionImpl @Inject constructor(
    val appNavigator: AppNavigator
) : UserDataContract.Direction{
    override suspend fun back() {
        appNavigator.back()
    }
}