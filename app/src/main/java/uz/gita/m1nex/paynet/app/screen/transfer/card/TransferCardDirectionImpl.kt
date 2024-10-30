package uz.gita.m1nex.paynet.app.screen.transfer.card

import uz.gita.m1nex.paynet.app.screen.addcard.AddCardScreen
import uz.gita.m1nex.paynet.app.screen.transfer.verify.TransferVerifyScreen
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.transfer.card.TransferCardContract
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class TransferCardDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : TransferCardContract.Direction {
    override suspend fun navigateToVerify(phone: String) {
        appNavigator.navigateTo(TransferVerifyScreen(phone))
    }

    override suspend fun navigateToAddCard() {
        appNavigator.navigateTo(AddCardScreen())
    }

    override suspend fun back() {
        appNavigator.back()
    }
}
