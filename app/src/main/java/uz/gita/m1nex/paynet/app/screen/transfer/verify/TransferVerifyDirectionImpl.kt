package uz.gita.m1nex.paynet.app.screen.transfer.verify

import uz.gita.m1nex.paynet.app.screen.home.tab.transfer.TransferTab
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.transfer.verify.TransferVerifyContract
import javax.inject.Inject

class TransferVerifyDirectionImpl @Inject constructor(
    val navigator: AppNavigator
): TransferVerifyContract.Direction {
    override suspend fun back() {
        navigator.backUntil(TransferTab::class.java)
    }

}