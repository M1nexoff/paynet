package uz.gita.m1nex.paynet.app.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite
import uz.gita.m1nex.paynet.app.ui.theme.component.TextBoldBlack
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite
import uz.gita.m1nex.paynet.app.ui.theme.Gray50
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.app.ui.theme.blackIcon
import uz.gita.m1nex.paynet.app.ui.theme.grayLight
import uz.gita.m1nex.paynet.app.ui.theme.pinkLight
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight

class DeleteLastItemsDialog(
    private val clickClear: () -> Unit ,
    private val clickCancel: () -> Unit ,
) : Screen {
    @Composable
    override fun Content() {
        DeleteLastItemsDialogContent(clickClear = {
            clickClear.invoke()
        }, clickCancel = {
            clickCancel.invoke()
        })
    }
}

@Composable
fun DeleteLastItemsDialogContent(
    clickCancel: () -> Unit, clickClear: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(BackgroundWhite)
    ) {


        Box(
            modifier = Modifier
                .padding(top = 16.dp)
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

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.ic_navigation_close_x24),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(grayLight)
                    .padding(8.dp)
                    .clickable(indication = null, interactionSource = remember {
                        MutableInteractionSource()
                    }) {
                        clickCancel.invoke()
                    },
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.delete_last_transfer_users),
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColorLight,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.really_delete_last_transfers),
            fontSize = 16.sp,
            color = blackIcon,
            fontFamily = FontFamily(Font(R.font.pnfont_medium)),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .weight(1f)
                    .clickable { clickCancel.invoke() }
                    .padding(end = 8.dp)
                    .border(width = 1.dp, color = blackIcon, shape = RoundedCornerShape(28.dp))
            ) {
                TextBoldBlack(
                    style = MaterialTheme.typography.labelLarge,
                    text = stringResource(id = R.string.cancel),
                    color = Color.Black
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(56.dp)
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .weight(1f)
                    .clickable { clickClear.invoke() }
                    .background(pinkLight)


            ) {
                TextBoldBlack(
                    style = MaterialTheme.typography.labelLarge,
                    text = stringResource(id = R.string.clear),
                    color = Color.Red
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddCardDialogDialogPreview() {
    PaynetOfficialTheme {
        DeleteLastItemsDialogContent({}, {})
    }
}
