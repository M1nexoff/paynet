package uz.m1nex.apps.mobile_banking.presentation.transfer_screens.transfers.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransferToCountry(
    modifier: Modifier,
    backgroundColor: Color,
    @DrawableRes icon: Int,
    title: String
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(color = backgroundColor)
    ) {

        Column(modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                text = title,
                fontSize = 18.sp,
                letterSpacing = 0.8.sp
            )

            Image(
                painter = painterResource(id = icon), contentDescription = null,
                modifier = Modifier
                    .height(96.dp)
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            )
        }
    }
}