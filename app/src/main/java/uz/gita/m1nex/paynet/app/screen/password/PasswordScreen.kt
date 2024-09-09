package uz.gita.m1nex.paynet.app.screen.password

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.PaynetOfficialTheme
import uz.gita.m1nex.paynet.app.ui.theme.main
import uz.gita.m1nex.presenter.screenmodel.password.PasswordContract
import uz.gita.m1nex.presenter.screenmodel.password.PasswordState
import uz.gita.m1nex.presenter.screenmodel.password.PasswordScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract
import java.lang.Error

class PasswordScreen(private val state: PasswordState = PasswordState.New) : Screen {
    @Composable
    override fun Content() {
        val viewModel: PasswordContract.Model = hiltScreenModel()
        Log.d("TTT", "Content: created")
        PasswordScreenContent(
            state = viewModel.collectAsState(),
            onEvent = viewModel::onEventDispatcher,
            passwordState = state
        )
        Log.d("TTT", "Content: end")
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun PasswordScreenPreview() {
    PaynetOfficialTheme {
        val state = remember {
            mutableStateOf(PasswordContract.UiState.Repeat)
        }
        Scaffold {
            PasswordScreenContent(state = state, onEvent = {}, passwordState = PasswordState.New)
        }
    }
}

@Composable
private fun PasswordScreenContent(
    state: State<PasswordContract.UiState>,
    onEvent: (PasswordContract.Intent) -> Unit,
    passwordState: PasswordState
) {
    when (state.value) {
        PasswordContract.UiState.Default -> {
            if (passwordState == PasswordState.New){
                PasswordEnterCode(
                    onPasswordEntered = { password ->
                        onEvent(PasswordContract.Intent.CreatePassword(password))
                    },
                    passwordState = passwordState
                )
            }else{
                PasswordEnterCode(
                    onPasswordEntered = { password ->
                        onEvent(PasswordContract.Intent.CheckPassword(password))
                    },
                    passwordState = passwordState
                )
            }
        }

        PasswordContract.UiState.Progress -> {
            Box {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        PasswordContract.UiState.Success -> {

        }

        is PasswordContract.UiState.Error -> {
            Box {
                PasswordEnterCode(
                    onPasswordEntered = { password ->
                        onEvent(PasswordContract.Intent.CheckPassword(password))
                    },
                    passwordState = passwordState,
                    isWrong = true
                )
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        PasswordContract.UiState.Repeat -> {
            PasswordEnterCode(
                onPasswordEntered = { password ->
                    onEvent(PasswordContract.Intent.CreatePassword(password))
                },
                passwordState = PasswordState.Check,
                isRepeat = true
            )
        }
    }
}
@Composable
private fun PasswordEnterCode(
    passwordState: PasswordState = PasswordState.New,
    onPasswordEntered: (String) -> Unit,
    isWrong: Boolean = false,
    isRepeat: Boolean = false
) {
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.18f)
        ) {
            Image(
                modifier = Modifier
                    .size(76.dp)
                    .align(Alignment.BottomCenter),
                painter = painterResource(id = R.drawable.logo_security),
                contentDescription = null
            )
        }

        val titleText = when {
            passwordState == PasswordState.New -> R.string.enter_pin_code
            isRepeat -> R.string.repeat_pin_code
            isWrong -> R.string.wrong_password
            else -> R.string.enter_pin_code
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            fontStyle = FontStyle.Normal,
            text = stringResource(id = titleText),
            fontFamily = main,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .size(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(
                            if (index >= password.value.length) R.drawable.ic_circle_outline else R.drawable.ic_circle_fill
                        ),
                        tint = if (index >= password.value.length) Color.Black else MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }
            }
        }
        Box(modifier = Modifier.fillMaxSize()){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (row in 0..2) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (col in 1..3) {
                            val number = row * 3 + col
                            NumberButton(number.toString()) {
                                if (password.value.length < 4) {
                                    password.value += number.toString()
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Spacer(modifier = Modifier.size(64.dp))
                    NumberButton("0") {
                        if (password.value.length < 4) {
                            password.value += "0"
                        }
                    }

                    IconButton(
                        modifier = Modifier.size(64.dp),
                        onClick = {
                            if (password.value.isNotEmpty()) {
                                password.value = password.value.dropLast(1)
                            }
                        }) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            painter = painterResource(id = R.drawable.ic_backspace),
                            contentDescription = "Backspace",
                            tint = Color.Black
                        )
                    }
                }
            }

        }
        if (password.value.length == 4) {
            onPasswordEntered(password.value)
        }
    }
}



@Composable
fun NumberButton(number: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(100))
            .clickable { onClick() }
        ,
        contentAlignment = Alignment.Center,

    ) {
        Text(
            text = number,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}
