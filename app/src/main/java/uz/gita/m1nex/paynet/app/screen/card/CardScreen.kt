package uz.gita.m1nex.paynet.app.screen.card

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import kotlinx.parcelize.Parcelize
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.core.getGradient
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.core.previewStateOf
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.dialog.DeleteCardDialog
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundLight
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite90
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.app.ui.theme.component.Card
import uz.gita.m1nex.paynet.app.ui.theme.component.ItemCard
import uz.gita.m1nex.paynet.app.ui.theme.component.TextBoldBlack
import uz.gita.m1nex.paynet.app.ui.theme.component.WhatIsThisCategoriesSection
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight90
import uz.gita.m1nex.paynet.app.ui.theme.white
import uz.gita.m1nex.presenter.screenmodel.card.CardContract

@Parcelize
class CardScreen(val data: CardData) : Screen,Parcelable {
    @Composable
    override fun Content() {
        val screenViewModel: CardContract.Model = hiltScreenModel()
        val uiState = screenViewModel.collectAsState()
        var cardIDRemember by remember {
            mutableStateOf("")
        }
        var cardPan by remember {
            mutableStateOf("")
        }
        var showDialog by remember {
            mutableStateOf(false)
        }
//        val localBottomSheetNavigator = LocalBottomSheetNavigator.current
        screenViewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is CardContract.SideEffect.OpenDialog -> {
                    cardPan = sideEffect.pan
                    cardIDRemember = sideEffect.cardId
                    showDialog = true
                }
            }
        }
        DeleteCardDialog(cardPan = cardPan, isVisible = showDialog, setShowDialog = {

        }, cancelRequest = {
            showDialog = false
        }) {
            screenViewModel.onEventDispatcher(CardContract.Intent.DeleteCard(cardIDRemember))
        }

        PaynetCardContent(
            data = data, uiState = uiState, onEventDispatcher = screenViewModel::onEventDispatcher
        )
    }
}


@Composable
fun PaynetCardContent(
    uiState: State<CardContract.UIState>,
    onEventDispatcher: (CardContract.Intent) -> Unit,
    data: CardData
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .wrapContentSize()
        ) {
            Image(painter = painterResource(id = R.drawable.ic_navigation_arrow_left_x24),
                contentDescription = null,
                modifier = Modifier
                    .padding()
                    .size(24.dp)
                    .clickable { })

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = data.name,
                color = textColorLight90,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                fontSize = 22.sp
            )
        }


        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(top = 56.dp, bottom = 10.dp)
                .verticalScroll(enabled = true, state = scrollState)
        ) {
            ItemCard(
                modifier = Modifier.padding(horizontal = 16.dp),
                data = data,
                gradient = getGradient(data.themeType)
            ) {

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 16.dp)
            ) {
                Card(modifier = Modifier
                    .height(80.dp)
                    .padding(end = 4.dp)
                    .clickable {
                        onEventDispatcher.invoke(
                            CardContract.Intent.ToP2PScreen(
                                data.pan,
                                data.owner
                            )
                        )
                    }
                    .weight(1f)
                    .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(white),
                    icon = R.drawable.ic_action_plus,
                    text = R.string.fill)
                Card(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(start = 4.dp, end = 4.dp)
                        .weight(1f)
                        .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                        .clickable {
                            //onEventDispatcher.invoke(HomeContract.Intent.OpenTransferScreen)
                        }, icon = R.drawable.ic_action_transfers, text = R.string.transact
                )
                Card(modifier = Modifier
                    .height(80.dp)
                    .padding(start = 4.dp)
                    .weight(1f)
                    .clickable {  }
                    .shadow(elevation = 3.dp, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(white),
                    icon = R.drawable.ic_operations_wallet,
                    text = R.string.pay)
            }
            CardCategoriesSection {
                onEventDispatcher.invoke(CardContract.Intent.OpenDialog(data.id, data.pan))
            }
        }
    }

}


@Composable
fun CardCategoriesSection(modifier: Modifier = Modifier, onDeleteCard: () -> Unit) {
    Column(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .fillMaxWidth()
    ) {
        WhatIsThisCategoriesSection(
            iconId = R.drawable.ic_operations_money,
            text1Id = R.string.cash_withdrawal,
            text2Id = R.string.by_paynet_agent
        )
        WhatIsThisCategoriesSection(
            iconId = R.drawable.ic_operations_cashback_2,
            text1Id = R.string.cashback_to_2_2,
            text2Id = R.string.fill_cashback_after_transfer,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Column(
            modifier = modifier
                .padding(top = 4.dp, bottom = 4.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 2.dp,
                    RoundedCornerShape(16.dp),
                    ambientColor = Color.White,
                    spotColor = Color(0xFF808080)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
                .padding(vertical = 12.dp, horizontal = 16.dp)
        ) {
            TextBoldBlack(text = stringResource(id = R.string.settings))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .padding(horizontal = 4.dp, vertical = 8.dp)
                    .clickable {

                    }, verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_operations_file_v2),
                    contentDescription = "Call"
                )

                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(id = R.string.card_style_and_colour),
                    style = MaterialTheme.typography.labelMedium,
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_chevron_right_x24),
                    contentDescription = "Call"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .padding(horizontal = 4.dp, vertical = 8.dp)
                    .clickable {
                        onDeleteCard.invoke()
                    }, verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    colorFilter = ColorFilter.tint(Color.Red),
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_operations_delete),
                    contentDescription = "Call"
                )

                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(id = R.string.delete_card),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Red
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_chevron_right_x24),
                    contentDescription = "Call"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaynetCardContentPreview() {
    PaynetOfficialTheme() {
        PaynetCardContent(previewStateOf(value = CardContract.UIState.InitState),{},CardData("", "Uzcard", 0L, "", "0909", 2020, 2, 1, false))
    }
}
