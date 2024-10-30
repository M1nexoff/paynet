package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.core.data.model.Child
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.textColor

@Composable
fun HistoryIncomeTransactionItem(
    data: Child,
    modifier: Modifier = Modifier, onClickItem: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp)
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Column(
            modifier = modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    }
                ) { onClickItem() },
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_operations_arrow_up),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(28.dp))
                        .background(Color(0xFFD8D7D7))
                        .size(46.dp)
                        .padding(8.dp)
                        .rotate(180f)

                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                        Text(
                            text = if (data.from.length >= 11) "${data.from.substring(0, 11)}.." else data.from,
                            fontSize = 16.sp,
                            color = Color.Black)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "${data.amount} so'm", fontSize = 16.sp, color = Color.Black)
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp)) {
                        Text(
                            text = if (data.type == "income") "Kiruvchi o'tkazma" else "Chiquvchi o'tkazma",
                            color = textColor,
                            fontSize = 12.sp,
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            painter = painterResource(id = R.drawable.ic_operations_credit_card),
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )

                        Text(
                            text = if (data.to.length >= 16) " • ${data.to.substring(12)}" else data.to,
                            fontSize = 12.sp,
                            color = Color.Black)
                    }
                }

            }
        }
    }
}
//@Preview
//@Composable
//fun HistoryTransactionIncomePreview() {
//    HistoryOutcomeTransactionItem(
//        data = HistoryTransaction(
//            "income",
//            "def def",
//            "6262470000009009",
//            1234,
//            1671350649653
//        ),
//        modifier = Modifier,
//        onClickItem = {
//
//        })
//}