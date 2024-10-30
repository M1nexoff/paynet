package uz.gita.m1nex.paynet.app.ui.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.core.getGradient
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.app.ui.theme.component.TextBoldBlack
import uz.gita.m1nex.paynet.app.ui.theme.textColor

class CardsSheetDialog(
    private val list: List<CardData>,
    private val onAddButtonClick: () -> Unit,
    private val onCardClick: (CardData) -> Unit,
) : Screen {
    @Composable
    override fun Content() {
        PaynetOfficialTheme {
            OptionBottomSheetContent(
                list = list,
                onAddButtonClick = onAddButtonClick,
                onCardClick = {
                    onCardClick.invoke(it)
                },
            )
        }
    }
}


@Composable
fun OptionBottomSheetContent(
    onAddButtonClick: () -> Unit,
    list: List<CardData>,
    onCardClick: (CardData) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.user_cards),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            color = Color.Gray,
            modifier = Modifier
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = list) {
                SheetCardItem(data = it, modifier = Modifier) {
                    onCardClick.invoke(it)
                }
            }
        }


        Spacer(modifier = Modifier.size(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(36.dp))
                .border(1.dp, color = Color.Black, shape = RoundedCornerShape(36.dp))
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .clickable {
                    onAddButtonClick.invoke()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_add_black),
                contentDescription = "Call",
            )

            TextBoldBlack(
                modifier = Modifier.padding(start = 8.dp),
                text = stringResource(id = R.string.add_card),
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}


@Composable
fun SheetCardItem(
    data: CardData,
    modifier: Modifier = Modifier,
    onClickItem: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(vertical = 4.dp)
            .clickable { onClickItem() },
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box( modifier = Modifier

                .clip(RoundedCornerShape(8.dp))
                .background(brush = getGradient(data.themeType))
                .height(48.dp)
                .width(64.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_humo), contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    TextBoldBlack(text = "${data.owner} • ${data.pan}", style = MaterialTheme.typography.labelLarge)
                }

                Row(modifier = Modifier.padding(bottom = 4.dp)) {
                    TextBoldBlack(
                        text = "${data.amount} so'm",
                        color = textColor
                    )
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
private fun OptionBottomSheetContentPreview() {
    PaynetOfficialTheme {
        val list = listOf(
            CardData("", "Gita Card", 0L, "AZZAm", "0036003600360036", 2020, 8, 2, false),
            CardData("", "Gita Card", 0L, "M1nex", "0036003600360036", 2020, 8, 2, false),
            CardData("", "Gita Card", 0L, "Abu", "0036003600360036", 2020, 8, 2, false),
            CardData("", "Gita Card", 0L, "Yusuf", "0036003600360036", 2020, 8, 2, false),
            CardData("", "Gita Card", 0L, "Abdulloh", "0036003600360036", 2020, 8, 2, false),
        )
        OptionBottomSheetContent(
            list = list,
            onAddButtonClick = {},
            onCardClick = {

            },
        )
    }
}