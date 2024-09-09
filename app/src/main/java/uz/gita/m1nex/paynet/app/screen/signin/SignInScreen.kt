package uz.gita.m1nex.paynet.app.screen.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.component.MaskVisualTransformation
import uz.gita.m1nex.paynet.app.ui.theme.main
import uz.gita.m1nex.presenter.screenmodel.signin.SignInContract
import uz.gita.m1nex.presenter.screenmodel.signin.SignInScreenModelImpl

class SignInScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: SignInContract.Model = hiltScreenModel()
        val uiState = viewModel.collectAsState()
        SignInScreenContent(
                state = uiState,
            viewModel::onEventDispatcher
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignInScreenContent(
    state: State<SignInContract.UiState>,
    eventDispatcher: (SignInContract.Intent) -> Unit
) {
    when(state.value){
        is SignInContract.UiState.Default -> {
            SignUp(eventDispatcher)
        }
        is SignInContract.UiState.Error -> {
            SignUp(eventDispatcher)
        }
        SignInContract.UiState.Progress -> {
            SignUp(eventDispatcher = eventDispatcher, false)
        }
    }
}

@Composable
private fun SignUp(eventDispatcher: (SignInContract.Intent) -> Unit, nextButtonEnabled: Boolean = true){
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Box {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Button(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                    onClick = { },
                    shape = RoundedCornerShape(35),
                    colors = ButtonDefaults.buttonColors(containerColor = LightGray),
                ) {
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = stringResource(id = R.string.language),
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontFamily = main,
                        fontStyle = FontStyle.Normal
                    )
                    Image(
                        modifier = Modifier
                            .width(56.dp)
                            .height(32.dp)
                            .padding(start = 4.dp),
                        painter = painterResource(id = R.drawable.uz),
                        contentDescription = null
                    )
                }
            }
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(76.dp),
                painter = painterResource(id = R.drawable.paynet),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.fill_fields),
                fontSize = 20.sp,
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontFamily = main,
            )
            Text(
                text = stringResource(id = R.string.for_being_client),
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                color = Color(0xFF8A8A8A),
                fontFamily = main,
            )

            TextField(
                singleLine = true,
                placeholder = { Text(stringResource(id = R.string.phone)) },
                value = phone.value,
                onValueChange = { input ->
                    if (input.length <= 9 && input.isDigitsOnly()) {
                        phone.value = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 12.dp)
                    .height(52.dp)
                    .background(Color.Transparent),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                visualTransformation = MaskVisualTransformation("+998 ## ### ## ##"),
                shape = RoundedCornerShape(30),

                )
            TextField(
                singleLine = true,
                placeholder = { Text(stringResource(id = R.string.password)) },
                value = password.value,
                onValueChange = { input ->
                    if (input.length <= 12 && !(input.contains(" "))) {
                        password.value = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 12.dp)
                    .height(56.dp)
                    .background(Color.Transparent),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(30),
            )

            var enabled = false
            if (password.value.length >= 8 && phone.value.length == 9) {
                enabled = true
            }
            Box(modifier = Modifier.fillMaxSize()) {
                Button(
                    onClick = {
                        if (password.value.length >= 8 && phone.value.length == 9) {
                            eventDispatcher.invoke(
                                SignInContract.Intent.SignIn(
                                    SignInRequest(
                                        "+998" + phone.value,
                                        password.value
                                    )
                                )
                            )
                        }
                    },
                    enabled = enabled && nextButtonEnabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    ),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.continue_text),
                        color = Color.White
                    )
                }

            }
        }
    }

}