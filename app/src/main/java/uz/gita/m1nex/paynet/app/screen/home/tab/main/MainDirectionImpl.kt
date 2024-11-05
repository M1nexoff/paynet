package uz.gita.m1nex.paynet.app.screen.home.tab.main

import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.paynet.app.screen.addcard.AddCardScreen
import uz.gita.m1nex.paynet.app.screen.card.CardScreen
import uz.gita.m1nex.paynet.app.screen.profile.ProfileScreen
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.presenter.screenmodel.home.tab.main.MainContract
import uz.gita.m1nex.presenter.screenmodel.transfer.card.TransferCardContract
import javax.inject.Inject

class MainDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): MainContract.Direction {
//    override suspend fun openCardsScreen(list: List<CardData>) {
//        val scope = CoroutineScope(Dispatchers.Main)
//        navigator.navigateTo(CardsSheetDialog(list,
//            {
//                scope.launch {
//                    navigator.navigateTo(AddCardScreen())
//                }
//            },{
//                scope.launch {
//                    navigator.navigateTo(CardScreen(it))
//                }
//            }
//        ))
//
//    }

    override suspend fun openPaynetCardScreen(data: CardData) {
        navigator.navigateTo(CardScreen(data))
    }

    override suspend fun openProfile() {
        navigator.navigateTo(ProfileScreen())
    }

    override suspend fun openAddScreen() {
        navigator.navigateTo(AddCardScreen())
    }

    override suspend fun openTransferScreen() {

    }

    override suspend fun openNotifications() {

    }

    override suspend fun openWhatIsThisScreen() {

    }

}