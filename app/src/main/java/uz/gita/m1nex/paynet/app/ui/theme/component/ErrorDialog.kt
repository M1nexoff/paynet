package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.getText

@Composable
fun ShowErrorDialog(message: MessageData) {
    AlertDialog(
        onDismissRequest = {  },
        confirmButton = {
            Button(onClick = { /* Dismiss or retry action */ }) {
                Text("OK")
            }
        },
        text = {
            Text(
                text = message.getText(), // Adjust based on how you handle MessageData
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        },
        title = {
            Text(
                text = "Error",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Red
            )
        }
    )
}
