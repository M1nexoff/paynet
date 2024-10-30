package uz.gita.m1nex.paynet.app.ui.dialog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import uz.gita.m1nex.core.data.model.Child
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.component.TextBoldBlack
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite90
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.app.ui.theme.gray
import uz.gita.m1nex.paynet.app.ui.theme.pinGreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransferOptionsSheet(val child: Child, private val onDismiss: () -> Unit) : Screen {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {

        PaynetOfficialTheme {
            TransferOptionSheetContent(child = child) {

                onDismiss.invoke()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun TransferOptionSheetContent(
    child: Child,
    onDismiss: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.transfer_options),
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                color = Color.Black,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier
                    .size(42.dp)
                    .padding(8.dp)
                    .clickable {
                        onDismiss.invoke()
                    },
                painter = painterResource(id = R.drawable.ic_navigation_close_x24),
                contentDescription = "Call"
            )

        }
        Spacer(modifier = Modifier.height(24.dp))

        Image(
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.ic_operations_arrow_up),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(28.dp))
                .background(gray)
                .size(48.dp)
                .padding(8.dp)
        )
        Text(
            text = "${child.amount.toString().toFormat(3)} so'm",
            fontSize = 32.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier.padding(top = 24.dp)
        )
        Text(text = "${formatTimestamp(child.time)}")
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)
        ) {
            Image(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(18.dp),
                painter = painterResource(id = R.drawable.ic_status_check),
                contentDescription = "Call"
            )

            Text(
                text = stringResource(id = R.string.operation_success),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                color = pinGreen,
                modifier = Modifier
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(108.dp)
                .wrapContentHeight()
                .padding(top = 16.dp)
                .shadow(elevation = 4.dp, RoundedCornerShape(16.dp), spotColor = Color.Gray)
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
                .padding(horizontal = 12.dp),
        ) {
            Image(
                modifier = Modifier
                    .padding()
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_action_download),
                contentDescription = "Call",
                colorFilter = ColorFilter.tint(pinGreen)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.download_check),
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                color = Color.Black,
                modifier = Modifier
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(BackgroundWhite90)
                .padding(start = 10.dp, top = 16.dp, end = 10.dp, bottom = 16.dp)
        ) {
            ItemTransferOption(firstData = "Yuboruvchi", secondData = "**** **** **** ${child.from}")
            ItemTransferOption(
                firstData = "Yuboruvchining ismi", secondData = "m1nex Maqsudov"
            )
            ItemTransferOption(firstData = "Qabul qiluvchi", secondData = "**** **** **** ${child.to}")
            ItemTransferOption(
                firstData = "Qabul qiluvchining nomi", secondData = "Muhammadrizo G'aniyev"
            )
            ItemTransferOption(firstData = "Tranzaksiya raqami", secondData = "233247782")
        }


    }
}

@Composable
fun ItemTransferOption(firstData: String, secondData: String) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = firstData)
        Spacer(modifier = Modifier.weight(1f))
        TextBoldBlack(text = secondData, textAlign = TextAlign.End)
    }
}


//@Composable
//@Preview(showBackground = true)
//private fun TransferOptionSheetContentPreview() {
//    MobileBankingTheme {
//        TransferOptionSheetContent(
//            { },
//        )
//    }
//    val showDialog = remember { mutableStateOf(false) }
//}


private fun CharSequence.toFormat(int: Int): String {
    val sb = StringBuilder()
    for (i in indices) {
        if (i % 3 == 0 && i != 0) {
            sb.append(" ")
        }
        sb.append(this[i])
    }
    return sb.toString()
}


fun formatTimestamp(timestamp: Long): String {
    val date = Date(timestamp)
    val sdf = SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault())
    return sdf.format(date)
}