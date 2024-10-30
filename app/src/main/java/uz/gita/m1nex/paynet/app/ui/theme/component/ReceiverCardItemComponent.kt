package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite90
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.app.ui.theme.iconColorPaleBlack
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight90
import uz.gita.m1nex.paynet.app.ui.theme.textTint
import uz.gita.m1nex.paynet.app.ui.theme.whiteLight

@Composable
fun ReceiverCardItemComponent(
    modifier: Modifier = Modifier,
    cardOwnerName: String,
    cardPan: String,
    onClick: () -> Unit,
) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
            .clip(RoundedCornerShape(16.dp))
            .background(BackgroundWhite90)
            .padding(horizontal = 12.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(
                elevation = 16.dp,
                shape = RectangleShape,
                clip = true,
                spotColor = whiteLight,
                ambientColor = whiteLight
            )
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) {
                onClick.invoke()
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_operations_credit_card),
            contentDescription = null,
            modifier = Modifier.width(56.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 12.dp)
                .fillMaxHeight()
        ) {
            Text(
                text = cardOwnerName,
                color = textTint,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_regular))
            )

            Text(
                text = addQuestionMark(cardPan),
                color = textColorLight90,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold))
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(id = R.drawable.ic_chevron_right_x24),
            contentDescription = null,
            tint = iconColorPaleBlack,
            modifier = Modifier
                .size(24.dp)
        )
    }
}

fun addQuestionMark(number: String): String {
    // Check if the length of the string is divisible by 4
    if (number.length % 4 != 0) {
        return "Invalid input: Number length must be a multiple of 4."
    }

    // Initialize an empty string to store the result
    var result = ""

    // Iterate through the string, adding question mark after every 4 digits
    for (i in number.indices step 4) {
        result += number.substring(i, i + 4) + " "
    }

    // Remove the extra question mark at the end
    result = result.dropLast(1)

    return result
}

@Preview(showBackground = true)
@Composable
fun ReceiverCardItemComponentPreview() {
    PaynetOfficialTheme {
        ReceiverCardItemComponent(
            cardOwnerName = "Axmedov Doniyor",
            cardPan = "8600120403327706"
        ) {}
    }
}