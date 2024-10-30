package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.cardColor
import uz.gita.m1nex.paynet.app.ui.theme.textColor

@Composable
fun CardP2PWithCardNumber(
    pankey:Int = 0,
    data: CardData,
    modifier: Modifier, onClickItem: () -> Unit,
    cardNumber: String = "9860 19** **** 3540"
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = modifier.clickable { onClickItem() },
        colors = CardDefaults.cardColors(containerColor = cardColor),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_operations_credit_card),
                contentDescription = null,
                modifier = Modifier
                    .height(36.dp)
                    .width(56.dp)

            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 2.dp)
            ) {
                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                    Text(text = data.owner, fontSize = 18.sp)
                }

                Row(modifier = Modifier.padding(bottom = 4.dp)) {
                    Text(text = if (pankey == 1) "•••• •••• •••• ${data.pan}" else maskCardNumber(data.pan), color = textColor)
                }
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_right_x24),
                    contentDescription = null
                )
            }
        }
    }
}
fun maskCardNumber(cardNumber: String): String {
    if (cardNumber.length != 16) {
        return cardNumber
    }

    return cardNumber.substring(0, 4) + " " +
            cardNumber.substring(4, 6) + " " +
            "•• •••• ••" +
            cardNumber.substring(14)
}


//@Preview
//@Composable
//private fun Preview() {
//    CardP2PSendItem(
//        modifier = Modifier
//    ) {
//
//    }
//}