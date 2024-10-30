package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.white

@Composable
fun ItemCard(modifier: Modifier, data: CardData, gradient: Brush, clickBack: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(224.dp)
            .padding(top = 16.dp)
            .clickable(interactionSource = remember {
                MutableInteractionSource()
            }, indication = null) {
                clickBack.invoke()
            }
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = gradient
            )
    ) {
        Row {
            Image(
                modifier = Modifier
                    .padding(top = 8.dp, start = 16.dp)
                    .size(42.dp)
                    .clip(RoundedCornerShape(21.dp))
                    .background(white)
                    .padding(10.dp),
                painter = painterResource(id = R.drawable.uz),
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier
                    .padding(top = 8.dp, end = 16.dp)
                    .size(42.dp)
                    .clip(RoundedCornerShape(21.dp))
                    .background(white)
                    .padding(10.dp),
                painter = painterResource(id = R.drawable.uz),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${data.amount.toString()} so'm",
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
        )
        Row {
            Text(
                text = "**** **** **** ${data.pan}",
                modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = if (data.expiredMonth < 10) "0${data.expiredMonth}/${
                    data.expiredYear.toString().substring(2)
                }" else "${data.expiredMonth}/${data.expiredYear.toString().substring(2)}",
                modifier = Modifier.padding(top = 8.dp, end = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
            )
        }

    }
}