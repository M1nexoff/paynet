package uz.gita.m1nex.paynet.app.ui.dialog

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.pinGreen


class BottomSheetCallCenterProfile() : Screen {
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
                    text = "Tel. +998 71-202-07-07",
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
                        val phone = "+998712020707"
                        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
                        context.startActivity(intent)

                    }
            ) {
                Text(
                    text = "Bekor qilish",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                    color = Color.Black
                )
            }


        }
    }
}