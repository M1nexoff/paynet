package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.ShadowColorCard
import uz.gita.m1nex.paynet.app.ui.theme.cardColor
import uz.gita.m1nex.paynet.app.ui.theme.grayColor
import uz.gita.m1nex.paynet.app.ui.theme.primaryColor
import uz.gita.m1nex.paynet.app.ui.theme.textColor

@Composable
fun CashBack(
    money: String = "",
    modifier: Modifier = Modifier,
    isVisibleMoney: Boolean
) {
//    .shadow(
//                elevation = 1.dp,
//                shape = RoundedCornerShape(16.dp),
//                ambientColor = ShadowColorCard,
//            )
//            .background(cardColor)
//            .padding(8.dp)
    Column(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = ShadowColorCard
            )
            .background(cardColor)
            .padding(8.dp)

    ) {
        Text(
            style = MaterialTheme.typography.bodyMedium,
            text = stringResource(R.string.cash_back_calculation),
            color = Black
        )

        Text(
            text = "",
            modifier = Modifier.padding(top = 8.dp),
            color = textColor,
            fontSize = 14.sp
        )

        Row {
            Text(
                text = if (isVisibleMoney) money else "• •••",
                style = MaterialTheme.typography.labelLarge,
                color = Black
            )

            Text(
                text = "\t" + stringResource(id = R.string.som),
                style = MaterialTheme.typography.labelLarge,
                color = textColor
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = ShadowColorCard
                )
                .background(White)
                .padding(4.dp)
        ) {

            Row(Modifier, verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(R.string.today), color = textColor, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(primaryColor)
                        .padding(2.dp)
                ) {
                    Text(color = White, text = if (isVisibleMoney) "0" else "••••", fontFamily = FontFamily(Font(R.font.pnfont_semibold)),fontSize = 12.sp)
                    Text(color = White, text = "\t" + stringResource(id = R.string.som),fontFamily = FontFamily(Font(R.font.pnfont_semibold)),fontSize = 12.sp)
                }
            }

            Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                repeat(7) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .padding(horizontal = 1.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(10f)
                                .fillMaxWidth()

                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(horizontal = 1.dp)
                                .clip(
                                    RoundedCornerShape(4.dp)
                                )
                                .background(grayColor)
                        )

                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun CashBackPreview() {
    CashBack(isVisibleMoney = true)
}