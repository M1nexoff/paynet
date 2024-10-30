package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.authComponentBg
import uz.gita.m1nex.paynet.app.ui.theme.textColor

//import uz.m1nex.apps.mobile_banking.R
//import uz.m1nex.apps.mobile_banking.data.room.TemplateTable
//import uz.m1nex.apps.mobile_banking.ui.components.TextNormal
//import uz.m1nex.apps.mobile_banking.ui.theme.authComponentBg
//import uz.m1nex.apps.mobile_banking.ui.theme.textColor

@Composable
fun Template(
    modifier: Modifier = Modifier,
    @DrawableRes imageID: Int,
    firstName: String,
    onClick:() -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick.invoke() }
            .width(60.dp)
    ) {
        Image(
            painter = painterResource(id = imageID),
            contentDescription = null,
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .size(60.dp)
                .border(
                    BorderStroke(2.dp, authComponentBg), CircleShape
                )
                .padding(1.dp)
                .clip(CircleShape)
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )

        Text(
            text = firstName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textColor,
            fontSize = 12.sp,
            letterSpacing = 0.8.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
private fun Preview() {
    Template(onClick = {},imageID = R.drawable.logo_tbc, firstName = "BOBOOR")
}