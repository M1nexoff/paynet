package uz.gita.m1nex.paynet.app.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.component.TextBoldBlack
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.app.ui.theme.grayIcon
import uz.gita.m1nex.paynet.app.ui.theme.grayLight

@Composable
fun LogOutDialog(
    isVisible: Boolean,
    setShowDialog: (Boolean) -> Unit,
    cancelRequest: () -> Unit,
    logOutRequest: () -> Unit
) {
    if (isVisible) {
        Dialog(properties = DialogProperties(), onDismissRequest = {
            cancelRequest.invoke()
        }) {
            Surface(
                shape = RoundedCornerShape(16.dp), color = Color.White
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextBoldBlack(
                            text = stringResource(id = R.string.really_logout),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextBoldBlack(
                        text = stringResource(id = R.string.really_logout_desc),
                        style = MaterialTheme.typography.displaySmall,
                        color = grayIcon
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier
                                .height(36.dp)
                                .clip(RoundedCornerShape(18.dp))
                                .background(grayLight)
                                .padding(horizontal = 16.dp)
                                .clickable {
                                    cancelRequest.invoke()
                                }, verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextBoldBlack(
                                text = stringResource(id = R.string.cancel),
                                style = MaterialTheme.typography.labelMedium,
                                color = grayIcon
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Row(
                            modifier = Modifier
                                .height(36.dp)
                                .clip(RoundedCornerShape(18.dp))
                                .background(grayLight)
                                .padding(horizontal = 16.dp)
                                .clickable {
                                    logOutRequest.invoke()
                                }, verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextBoldBlack(
                                text = stringResource(id = R.string.log_out),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Red
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun TransferOptionSheetContentPreview() {
    PaynetOfficialTheme {
        val showDialog = remember { mutableStateOf(false) }

        LogOutDialog(setShowDialog = {
            showDialog.value = it
        }, isVisible = false, logOutRequest = {}, cancelRequest = {

        })
    }

}