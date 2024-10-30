package uz.gita.m1nex.paynet.app.screen.card

import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.card.CardContract
import javax.inject.Inject

class CardDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator,
) : CardContract.Direction {

    override suspend fun back() {
        appNavigator.back()
    }

}