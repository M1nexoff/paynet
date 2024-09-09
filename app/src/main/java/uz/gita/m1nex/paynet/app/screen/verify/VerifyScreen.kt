package uz.gita.m1nex.paynet.app.screen.verify

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import kotlinx.coroutines.flow.flow
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.app.ui.theme.main
import uz.gita.m1nex.presenter.screenmodel.verify.VerifyContract
import uz.gita.m1nex.presenter.screenmodel.verify.VerifyScreenModelImpl

class VerifyScreen(private val phone: String, private val isSignIn: Boolean) : Screen {
    @Composable
    override fun Content() {
        val viewModel: VerifyContract.Model = hiltScreenModel()
        VerifyScreenContent(
            state = viewModel.collectAsState(),
            viewModel::onEventDispatcher,
            phone,
            isSignIn
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyScreenContent(
    state: State<VerifyContract.UiState>?,
    onEventDispatcher: (VerifyContract.Intent) -> Unit,
    phone: String,
    isSignIn: Boolean
) {
    val remainingTime = remember { mutableStateOf(0) }

    when (state?.value) {
        is VerifyContract.UiState.Time -> {
            remainingTime.value = (state.value as VerifyContract.UiState.Time).time
        }
        VerifyContract.UiState.Default -> {
            remainingTime.value = 0
        }
        else -> {}
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                onEventDispatcher.invoke(VerifyContract.Intent.Back)
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            contentDescription = null,
            painter = painterResource(id = R.drawable.paynet),
            modifier = Modifier
                .size(80.dp)
        )
        Text(
            modifier = Modifier.padding(top = 30.dp),
            text = stringResource(id = R.string.write_code_from_sms),
            fontSize = 24.sp,
            color = Color.Black,
            fontStyle = FontStyle.Normal,
            fontFamily = main
        )
        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = phone, fontSize = 16.sp, color = Color.Black)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.code_send_to_phone),
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        val focusRequesters = List(6) { FocusRequester() }
        val otpValues = remember { mutableStateOf(List(6) { "" }) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            otpValues.value.forEachIndexed { index, value ->
                TextField(
                    value = value,
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .onKeyEvent {
                            if (it.key == Key.Backspace && index != 0 && otpValues.value[index].isEmpty()) {
                                otpValues.value = otpValues.value
                                    .toMutableList()
                                    .apply {
                                        this[index] = ""
                                    }
                                focusRequesters[index - 1].requestFocus()
                                true
                            } else {
                                false
                            }
                        }
                        .width(48.dp)
                        .height(60.dp)
                        .focusRequester(focusRequesters[index]),
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.isDigitsOnly()) {
                            otpValues.value = otpValues.value.toMutableList().apply {
                                this[index] = newValue
                            }
                            if (newValue.isNotEmpty() && index < 5) {
                                focusRequesters[index + 1].requestFocus()
                            } else if (newValue.isEmpty() && index > 0) {
                                focusRequesters[index - 1].requestFocus()
                            }
                        } else if (newValue.length == 2 && newValue.isDigitsOnly() && index < 5) {
                            otpValues.value = otpValues.value.toMutableList().apply {
                                this[index] = newValue.first().toString()
                                this[index + 1] = newValue.last().toString()
                            }
                            if (index < 4) {
                                focusRequesters[index + 2].requestFocus()
                            }

                        }
                        if (otpValues.value.all { it.isNotEmpty() }){
                            val code = otpValues.value.joinToString("")
                            onEventDispatcher.invoke(
                                if (isSignIn) VerifyContract.Intent.SignInVerify(code)
                                else VerifyContract.Intent.SignUpVerify(code)
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    visualTransformation = VisualTransformation.None,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                        color = Color.Black
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                onEventDispatcher.invoke(
                    if (isSignIn) VerifyContract.Intent.SignInResend
                    else VerifyContract.Intent.SignUpResend
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = if (remainingTime.value > 0) LightGray else Color.Blue),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = remainingTime.value == 0
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = if (remainingTime.value > 0) Color.Gray else Color.White,
                    modifier = Modifier
                        .size(28.dp)
                )
                Text(
                    fontFamily = main,
                    fontStyle = FontStyle.Normal,
                    text = if (remainingTime.value > 0) "${remainingTime.value} sek" else stringResource(id = R.string.new_code_send),
                    color = if (remainingTime.value > 0) Color.Gray else Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth(0.7f)
                )
            }
        }
    }
}