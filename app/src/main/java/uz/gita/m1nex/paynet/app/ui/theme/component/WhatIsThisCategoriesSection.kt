package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite90
import uz.gita.m1nex.paynet.app.ui.theme.Gray70
import uz.gita.m1nex.paynet.app.ui.theme.grayIcon
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight90

@Composable
fun WhatIsThisCategoriesSection(
    modifier: Modifier = Modifier,
    iconId: Int,
    text1Id: Int,
    text2Id: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(top = 4.dp, bottom = 4.dp)
            .fillMaxWidth()
            .shadow(elevation = 2.dp, RoundedCornerShape(16.dp), ambientColor = Color.White, spotColor = Color(0xFF808080))
            .clip(RoundedCornerShape(16.dp))
            .background(BackgroundWhite90)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = grayIcon,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(26.dp)
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(id = text1Id),
                color = textColorLight90,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_regular))
            )

            Text(
                text = stringResource(id = text2Id),
                color = Gray70,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                textAlign = TextAlign.Start,
                lineHeight = 16.sp
            )
        }
    }
}
