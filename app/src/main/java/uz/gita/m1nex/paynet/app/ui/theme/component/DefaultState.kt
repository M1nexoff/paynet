package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.core.data.model.transfer.LastTransferData
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.blueLight
import uz.gita.m1nex.paynet.app.ui.theme.greenLight
import uz.gita.m1nex.paynet.app.ui.theme.pinkExtraLight
import uz.gita.m1nex.paynet.app.ui.theme.pinkLight

@Composable
fun DefaultState(
//    templates:List<LastTransferData>,
    list: List<LastTransferData>,
    onClickLastPayedCard: (LastTransferData) -> Unit,
    onClickTemplate: (LastTransferData) -> Unit,
    onClickAddTemplate: () -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(start = 12.dp), modifier = Modifier.padding(top = 24.dp)
    ) {
        items(list.size) {
            LastPayedCards(
                modifier = Modifier.padding(horizontal = 8.dp),
                    imageID = R.drawable.logo_tbc,
                firstName = list[it].owner,
                lastName = "",
                onClick = {
                    onClickLastPayedCard.invoke(list[it])
                }
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 12.dp, end = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .height(96.dp)
                .padding(end = 6.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(pinkLight)
        ) {
            Text(
                stringResource(id = R.string.mine_card),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            )
            Image(
                painter = painterResource(id = R.drawable.self_transfer_to_card),
                modifier = Modifier
                    .weight(1f)
                    .size(96.dp)
                    .align(Alignment.CenterVertically)
                    .padding(),
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .height(96.dp)
                .padding(start = 6.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(greenLight)
        ) {
            Text(
                stringResource(id = R.string.to_paynet_card),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            Image(
                painter = painterResource(id = R.drawable.self_transfer_to_wallet),
                modifier = Modifier
                    .size(84.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxSize()
                    .weight(1f)
                    .padding(),
                contentDescription = null
            )
        }
    }

    Text(
        text = stringResource(R.string.template),
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 24.dp, start = 12.dp)
    )

//    LazyRow(
//        modifier = Modifier.padding(top = 12.dp),
//        contentPadding = PaddingValues(start = 12.dp),
//    ) {
//        item {
//            AddTemplate(Modifier.padding(end = 24.dp), onClick = {
//                onClickAddTemplate.invoke()
//            })
//        }
//        items(templates.size) {index ->
//            Template(
//                modifier = Modifier.padding(end = 24.dp), firstName = templates[index].templateName, imageID = R.drawable.logo_tbc
//            ) {
//                onClickTemplate.invoke(templates[index])
//            }
//        }
//    }

    Text(
        text = stringResource(R.string.international_transactions),
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 36.dp, start = 12.dp)
    )
    Spacer(modifier = Modifier.size(16.dp))
    Row(
        Modifier
            .padding(horizontal = 12.dp)) {
        Column(
            modifier = Modifier
                .weight(1f)
                .height(124.dp)
                .padding(end = 6.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(blueLight)
        ) {
            Text(
                stringResource(id = R.string.to_rus),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.uzb_to_ru),
                modifier = Modifier
                    .size(124.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(end = 8.dp),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .height(124.dp)
                .padding(start = 6.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(pinkExtraLight)
        ) {
            Text(
                stringResource(id = R.string.to_uzbekistan),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)

            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ru_to_uzb),
                modifier = Modifier
                    .size(108.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(end = 8.dp),
                contentDescription = null
            )
        }
    }

}
