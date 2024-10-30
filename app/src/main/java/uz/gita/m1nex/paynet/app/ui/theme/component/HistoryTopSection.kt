//package uz.m1nex.apps.mobile_banking.presentation.history.components
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import uz.m1nex.apps.mobile_banking.R
//
//@Composable
//fun HistoryTopSection(modifier: Modifier, onClick: () -> Unit) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = modifier
//    ) {
//        Text(
//            text = stringResource(id = R.string.history),
//            textAlign = TextAlign.Center,
//            fontSize = 32.sp,
//            color = Color.Black,
//            modifier = Modifier.padding(),
//            fontFamily = FontFamily(
//                Font(R.font.pnfont_semibold)
//            )
//        )
//        Spacer(modifier = Modifier.weight(1f))
//        Image(
//            painter = painterResource(id = R.drawable.ic_navigation_search_x24),
//            contentDescription = null,
//            modifier = Modifier.size(24.dp).clickable {
//                onClick.invoke()
//            }
//        )
//    }
//}