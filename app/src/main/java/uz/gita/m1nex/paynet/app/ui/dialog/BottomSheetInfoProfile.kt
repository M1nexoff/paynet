package uz.gita.m1nex.paynet.app.ui.dialog

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.pinGreen

class BottomSheetInfoProfile() : Screen {
    @Composable
    override fun Content() {
        BottomSheetInfoContent()
    }

}
@Composable
fun BottomSheetInfoContent(
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
            text = stringResource(id = R.string.want_paynet_to_you),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            color = Color.Black,
            modifier = Modifier
        )

        Text(
            text = stringResource(id = R.string.sheet_info_description),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_medium)),
            color = Color(0xFF6D6D6D),
            modifier = Modifier.padding(top = 12.dp)
        )



        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(pinGreen)
                .clickable {

                }
        ) {
            Text(
                text = stringResource(id = R.string.all_ok),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                color = Color.White
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(16.dp))
                .clickable {
                    val uri =
                        "https://play.google.com/store/apps/details?id=uz.paynet.app&hl=uz"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    context.startActivity(intent)
                }
        ) {
            Text(
                text = stringResource(id = R.string.some_bad),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                color = Color.Black
            )
        }


    }
}
