package uz.gita.m1nex.paynet.app.screen.signin

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.core.screen.Screen
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.m1nex.core.getText
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.component.MaskVisualTransformation
import uz.gita.m1nex.paynet.app.ui.theme.setLanguage
import uz.gita.m1nex.presenter.screenmodel.signin.SignInContract
import java.util.Locale

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

@Preview
@Composable
fun prew() {
    SignInScreenContent(state = remember {
        mutableStateOf(
            SignInContract.UiState.Default(
                SignInRequest("", "")
            )
        )
    }, eventDispatcher = {})
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignInScreenContent(
    state: State<SignInContract.UiState>,
    eventDispatcher: (SignInContract.Intent) -> Unit
) {
    when (state.value) {
        is SignInContract.UiState.Default -> {
            SignUp(eventDispatcher)
        }

        is SignInContract.UiState.Error -> {
            val errorState = state.value as SignInContract.UiState.Error
            SignUp(eventDispatcher = eventDispatcher, errorMessage = errorState.message.getText())
        }

        SignInContract.UiState.Progress -> {
            SignUp(eventDispatcher = eventDispatcher, false)
        }
    }
}

@Composable
private fun SignUp(
    eventDispatcher: (SignInContract.Intent) -> Unit,
    nextButtonEnabled: Boolean = true,
    errorMessage: String? = null
) {
    val phone = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val update = remember { mutableStateOf(0) }
    if (update.value != -1) {
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                Modifier.clickable { eventDispatcher.invoke(SignInContract.Intent.Back) })
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
                    val context = LocalContext.current
                    Button(
                        modifier = Modifier
                            .align(Alignment.CenterEnd),
                        onClick = {
                            Log.d("QQQ", "SignUp: lang")
                            when (Locale.getDefault().language) {
                                "en" -> {
                                    setLanguage(Locale("uz"), context)
                                }

                                else -> {
                                    setLanguage(Locale.ENGLISH, context)
                                }

                            }
                            update.value += 1
                        },
                        shape = RoundedCornerShape(35),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(
                                red = 230,
                                green = 224,
                                blue = 233
                            )
                        ),
                    ) {
                        update.value!=-1
                        Text(
                            text = stringResource(id = R.string.language),
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Image(
                            modifier = Modifier
                                .width(56.dp)
                                .height(32.dp),
                            painter = when (Locale.getDefault().language) {
                                "uz" -> painterResource(id = R.drawable.uz)
                                else -> painterResource(id = R.drawable.en)
                            },
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
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = stringResource(id = R.string.for_being_client),
                    color = Color(0xFF8A8A8A),
                    style = MaterialTheme.typography.bodyLarge,
                )

                TextField(
                    singleLine = true,
                    placeholder = {
                        update.value
                        Text(stringResource(id = R.string.phone)) },
                    value = phone.value,
                    onValueChange = { input ->
                        if (input.length <= 9 && input.isDigitsOnly() && update.value != -1) {
                            phone.value = input
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 12.dp)
                        .height(56.dp)
                        .clickable { update.value + 1 }
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
                    placeholder = {
                        if (update.value != -1) {
                            Text(stringResource(id = R.string.password))
                        }
                    },
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
                    if (errorMessage != null){
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
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
                            .padding(bottom = 16.dp)
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(horizontal = 24.dp)
                    ) {
                        update.value
                        Text(
                            text = stringResource(id = R.string.continue_text),
                            color = Color.White
                        )
                    }

                }
            }
        }

    }
}