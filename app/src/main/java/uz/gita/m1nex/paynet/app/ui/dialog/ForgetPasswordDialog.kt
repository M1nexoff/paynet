package uz.gita.m1nex.paynet.app.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite90
import uz.gita.m1nex.paynet.app.ui.theme.Gray70
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.app.ui.theme.pinGreen
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight90
import uz.gita.m1nex.paynet.app.ui.theme.white

class ForgetPasswordDialog(
    val onClick: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundWhite90)
                .padding(16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(2.5.dp))
                        .background(Color(0xffebedef))
                )
            }

            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_navigation_close_x24),
                    contentDescription = null,
                    tint = Gray70,
                    modifier = Modifier.size(26.dp)
                )
            }

            Text(
                text = stringResource(id = R.string.forget_pin),
                maxLines = 1,
                color = textColorLight90,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.pin_code_recovery_conditions),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_medium)),
                color = Gray70,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(pinGreen)
                    .clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) { onClick.invoke() }
            ) {
                androidx.compose.material.Text(
                    text = stringResource(id = R.string.exit),
                    color = white,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }

}

@Composable
fun ForgetPasswordDialogContent() {
    ForgetPasswordDialog {

    }
}

@Preview(showBackground = true)
@Composable
fun ForgetPasswordDialogContentPreview() {
    PaynetOfficialTheme {
        ForgetPasswordDialogContent()
    }
}