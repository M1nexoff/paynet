package uz.gita.m1nex.paynet.app.ui.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme

class OptionBottomSheetDialog(
    private val onFirst: () -> Unit,
    private val onSecond: () -> Unit,
    private val onThird: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        PaynetOfficialTheme {
            OptionBottomSheetContent(
                onFirst = onFirst,
                onSecond = onSecond,
                onThird = onThird
            )
        }
    }
}

@Composable
fun OptionBottomSheetContent(
    onFirst: () -> Unit,
    onSecond: () -> Unit,
    onThird: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.info_profile),
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            color = Color.Black,
            modifier = Modifier
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .padding(horizontal = 4.dp, vertical = 8.dp)
                .clickable {
                    onFirst.invoke()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.ic_operations_file_v2),
                contentDescription = "Call"
            )

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.public_offer),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.ic_chevron_right_x24),
                contentDescription = "Call"
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .padding(horizontal = 4.dp)
                .clickable {
                    onSecond.invoke()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.ic_operations_file_v2),
                contentDescription = "Edit"
            )

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.paynet_bonus_card),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.ic_chevron_right_x24),
                contentDescription = "Call"
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .padding(horizontal = 4.dp)
                .clickable {
                    onThird.invoke()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.ic_operations_file_v2),
                contentDescription = "Delete"
            )
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.paynet_bonus_card),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.ic_chevron_right_x24),
                contentDescription = "Call"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun OptionBottomSheetContentPreview() {
    PaynetOfficialTheme{
        OptionBottomSheetContent(
           { },
            { },
            { },
        )
    }
}