package uz.gita.m1nex.paynet.app.screen.home.tab.transfer

import uz.gita.m1nex.paynet.app.screen.transfer.card.TransferCardScreen
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.transfer.TransferContract
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class TransferDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : TransferContract.Direction {
    override suspend fun toP2PScreen(cardReceiverPan: String, cardOwner: String) {
        appNavigator.navigateTo(TransferCardScreen(cardReceiverPan, cardOwner))
    }
}
