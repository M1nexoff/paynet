package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme

@Composable
fun OutLineButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) Color.Transparent else Color(0xFFDDF0D6),
            contentColor = if (enabled) Color.Black else Color(0xFFB1D0A3)
        ),
        border = if (enabled) BorderStroke(1.dp, Color.Black) else null,
        shape = RoundedCornerShape(100),
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) Color(0xFF17AD4F) else Color(0xFFEBECEE),
            contentColor = if (enabled) Color.White else Color(0xFF8A8B8F)
        ),
        shape = RoundedCornerShape(100),
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAppButton() {
    PaynetOfficialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            AppButton(text = "Sign in", modifier = Modifier.padding(start = 16.dp), onClick = {})
            AppButton(text  ="Sign in", enabled = false, onClick = {})
        }
    }
}