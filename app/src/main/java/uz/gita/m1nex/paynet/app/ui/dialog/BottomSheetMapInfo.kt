package uz.gita.m1nex.paynet.app.ui.dialog
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.pinGreen
import uz.gita.m1nex.paynet.app.ui.theme.textColor

class BottomSheetMapInfo(
    val name: String,
    val workTime: String,
    val imageRes: Int,
    val locationText: String,
    val phoneNumber: String,
//    val latLng: LatLng
) : Screen {
    @Composable
    override fun Content() {
        BottomSheetContent()
    }

    @Composable
    fun BottomSheetContent(
    ) {
        val modifier: Modifier = Modifier
        val context = LocalContext.current
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        )
        {
            Text(
                text = name,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                color = Color.Black,
                modifier = Modifier
            )

            Text(
                text = "Moliyaviy tashkilot",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                color = Color(0xFF6D6D6D),
                modifier = Modifier.padding(top = 12.dp)
            )

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Paynet",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(14.dp))
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(32.dp)
                )

                Text(
                    text = locationText,
                    fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                    fontSize = 16.sp
                )
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(32.dp)
                )

                Text(
                    text = workTime,
                    fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                    fontSize = 16.sp
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(32.dp)
                )

                Text(
                    text = phoneNumber,
                    fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                    fontSize = 16.sp
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(pinGreen)
                    .clickable {
//                        val uri = "https://www.google.com/maps/dir/41.35616623619039,69.28782337575794/Paynet/@41.2773795,69.2293955,14z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x38ae8ac4a0d3c7e5:0x2fc321900b2c079b!2m2!1d${latLng.longitude}!2d${latLng.latitude}?entry=ttu"
//                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
//                        context.startActivity(intent)
                    }
            ) {
                Text(
                    text = "Marshrut",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    color = Color.White
                )
            }


        }
    }
}