package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.paynet.R

@OptIn(ExperimentalFoundationApi::class, ExperimentalUnitApi::class)
@Composable
fun CardItem(
    cardData: CardData,
    onClick: () -> Unit,
) {

    Surface(modifier = Modifier
        .wrapContentHeight()
        .padding(vertical = 0.dp, horizontal = 4.dp)
        .fillMaxWidth()

        .background(Color.White)
        .height(64.dp)

        .combinedClickable(
            onClick = { onClick.invoke() },
            onLongClick = { /*onLongClick.invoke()*/ }
        )
    ) {

        Row(modifier = Modifier.fillMaxHeight()) {


            Image(
                painterResource(id = R.drawable.ic_operations_credit_card),
                contentDescription = "MusicDisk",
                modifier = Modifier
                    .padding(4.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .align(Alignment.CenterVertically)
                //.background(Color(0XFF988E8E), RoundedCornerShape(8.dp))
            )


            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = cardData.owner,
                    color = Color.Black,
                    fontSize = TextUnit(16f, TextUnitType.Sp),
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "**** **** **** ${cardData.pan}",
                    color = Color(0XFF988E8E),
                    fontSize = TextUnit(14f, TextUnitType.Sp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(
                    modifier = Modifier
                        .height(0.dp)
                        .weight(1f)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 1.dp, bottom = 2.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Gray)
                ) {}
            }
        }
    }
}