package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.authComponentBg
import uz.gita.m1nex.paynet.app.ui.theme.grayIcon
import uz.gita.m1nex.paynet.app.ui.theme.textColor

@Composable
fun AddTemplate(
    modifier: Modifier,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(authComponentBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_black),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                tint = grayIcon
            )
        }

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )

        Text(
            text = stringResource(id = R.string.add),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textColor,
            fontSize = 12.sp,
            letterSpacing = 0.8.sp,
            textAlign = TextAlign.Center,
        )
    }
}