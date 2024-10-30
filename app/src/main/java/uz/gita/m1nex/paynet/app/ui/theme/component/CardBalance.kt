package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.core.toFormat
import uz.gita.m1nex.paynet.app.ui.theme.cardColor
import uz.gita.m1nex.paynet.app.ui.theme.textColor

@Composable
fun CardBalance(
    text:String,
    modifier: Modifier, onClickItem: () -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = modifier.clickable { onClickItem() },
        colors = CardDefaults.cardColors(containerColor = cardColor),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(modifier = Modifier.padding()) {
                    Text(text = "Balans", color = textColor, fontSize = 12.sp, fontWeight = FontWeight.W600)
                }
                Row(modifier = Modifier.padding()) {
                    Text(text = text.toFormat(3), fontSize = 24.sp,fontWeight = FontWeight.W600)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "so'm", fontSize = 22.sp, color = Color.Gray,fontWeight = FontWeight.W600)
                }

            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CardBalance(
        text = "20000",
        modifier = Modifier
    ) {

    }
}