package uz.gita.m1nex.paynet.app.screen.card

import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.paynet.app.screen.home.tab.main.MainTab
import uz.gita.m1nex.paynet.app.screen.transfer.card.TransferCardScreen
import uz.gita.m1nex.paynet.app.screen.update.CardUpdateScreen
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.card.CardContract
import javax.inject.Inject

class CardDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator,
) : CardContract.Direction {
    override suspend fun openP2PScreen(pan: String, owner: String) {
        appNavigator.navigateTo(TransferCardScreen(pan, owner))
    }

    override suspend fun back() {
        appNavigator.back()
    }

    override suspend fun backUntilHome() {
        appNavigator.backUntil(MainTab::class.java)
    }

    override suspend fun openUpdate(card: CardData) {
        appNavigator.replace(CardUpdateScreen(card))
    }

}