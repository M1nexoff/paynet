package uz.gita.m1nex.paynet.app.ui.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite
import uz.gita.m1nex.paynet.app.ui.theme.Gray50
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight80

class AddCardDialog(
    val clickUzb: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        AddCardDialogDialog(clickUzb = {
            clickUzb.invoke()
        })
    }
}

@Composable
fun AddCardDialogDialog(
    clickUzb: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(BackgroundWhite)
    ) {

        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        Box(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp)
                .height(4.dp)
                .width(46.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Gray50)
                .align(Alignment.CenterHorizontally)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.what_want_to_add),
                fontSize = 20.sp,
                color = textColorLight,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.ic_navigation_close_x24),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) {
                        bottomSheetNavigator.hide()
                    },
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp, top = 24.dp)
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    }
                ) { clickUzb.invoke() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.uz),
                contentDescription = "uzb card",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(32.dp)
            )

            Text(
                text = stringResource(id = R.string.uzb_card),
                color = textColorLight80,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_regular))
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 26.dp, bottom = 34.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ru),
                contentDescription = "uzb card",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(32.dp)
            )

            Text(
                text = stringResource(id = R.string.rus_card),
                color = textColorLight80,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_regular))
            )
        }
    }
}


@Preview()
@Composable
private fun AddCardDialogPreview() {
    PaynetOfficialTheme {
        AddCardDialogDialog({})
    }
}
