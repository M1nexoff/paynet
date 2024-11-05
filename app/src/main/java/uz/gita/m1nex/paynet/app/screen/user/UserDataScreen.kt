package uz.gita.m1nex.paynet.app.screen.user

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LifecycleEffectOnce
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.ozcanalasalvar.datepicker.model.Date
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.m1nex.core.data.model.UpdateInfoRequest
import uz.gita.m1nex.core.data.model.sign.SignUp
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.core.previewStateOf
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.screen.addcard.TopSection
import uz.gita.m1nex.paynet.app.screen.signup.WheelDatePicker
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite90
import uz.gita.m1nex.paynet.app.ui.theme.buttonVisibleColor
import uz.gita.m1nex.paynet.app.ui.theme.component.AppButton
import uz.gita.m1nex.paynet.app.ui.theme.component.TextBoldBlack
import uz.gita.m1nex.paynet.app.ui.theme.mainBgLight
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract
import uz.gita.m1nex.presenter.screenmodel.user.UserDataContract
import java.util.Calendar

class UserDataScreen : Screen {
    @OptIn(ExperimentalVoyagerApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        val screenModel: UserDataContract.Model = hiltScreenModel()
        val uiState = screenModel.collectAsState()
        LifecycleEffectOnce {
            screenModel.onEventDispatcher(UserDataContract.Intent.GetData)
        }
        UserDataContent(uiState = uiState, screenModel::onEventDispatcher)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun UserDataContent(
    uiState: State<UserDataContract.UiState>, onEventDispatcher: (UserDataContract.Intent) -> Unit
) {
    val context = LocalContext.current
    val firstName = rememberSaveable { mutableStateOf("") }
    val lastName = rememberSaveable { mutableStateOf("") }
    val bornDate = rememberSaveable { mutableStateOf(System.currentTimeMillis().toString()) }
    val gender = rememberSaveable { mutableStateOf(false) }
    val focusRequesters = List(4) { FocusRequester() }
    val nextButtonEnabled = rememberSaveable { mutableStateOf(true) }
    when(uiState.value){
        is UserDataContract.UiState.Data -> {
            firstName.value = (uiState.value as UserDataContract.UiState.Data).user.firstName
            lastName.value = (uiState.value as UserDataContract.UiState.Data).user.lastName
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = (uiState.value as UserDataContract.UiState.Data).user.bornDate
            bornDate.value = calendar.timeInMillis.toString()
            gender.value = (uiState.value as UserDataContract.UiState.Data).user.gender == 0
        }
        UserDataContract.UiState.Init -> {

        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBgLight)
            .padding(horizontal = 16.dp)
    ) {
        TopSection(text = R.string.personal_cabinet, modifier = Modifier) {
            onEventDispatcher.invoke(UserDataContract.Intent.Back)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .padding( top = 16.dp, bottom = 16.dp)
        ) {

            TextField(
                singleLine = true,
                placeholder = {
                    Text(stringResource(id = R.string.first_name)) },
                value = firstName.value,
                onValueChange = { input ->
                    if (input.length <= 12 && !(input.any { !it.isLetter() })) {
                        firstName.value = input
                    }
                },
                keyboardActions = KeyboardActions(
                    onDone = { focusRequesters[3].requestFocus() }
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 12.dp)
                    .focusRequester(focusRequesters[2])
                    .height(56.dp)
                    .background(Color.Transparent),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(30),
            )
            TextField(
                singleLine = true,
                placeholder = {
                    Text(stringResource(id = R.string.last_name)) },
                value = lastName.value,
                onValueChange = { input ->
                    if (input.length <= 15 && !(input.any { !it.isLetter() })) {
                        lastName.value = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 12.dp)
                    .focusRequester(focusRequesters[3])
                    .height(56.dp)
                    .background(Color.Transparent),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(30),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(top = 24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(start = 24.dp)
                ) {
                    WheelDatePicker(
                        offset = 2,
                        startDate = Date(bornDate.value.toLong()),
                        backgroundColor = MaterialTheme.colorScheme.background,
                        selectorEffectEnabled = true,
                        yearsRange = IntRange(1900, 2024),
                        darkModeEnabled = false,
                        onDateChanged = { day, month, year, date ->
                            val calendar = Calendar.getInstance()
                            calendar.set(year, month - 1, day, 0, 0, 0)
                            bornDate.value = calendar.timeInMillis.toString()
                        }
                    )

                }
                Column(Modifier.align(Alignment.CenterVertically)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                gender.value = !(gender.value)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.LightGray
                            ),
                            checked = !(gender.value), onCheckedChange = {
                                gender.value = !(gender.value)
                            }
                        )

                        Text(text = stringResource(id = R.string.male))
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                gender.value = !(gender.value)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.LightGray
                            ),
                            checked = gender.value, onCheckedChange = {
                                gender.value = it
                            }
                        )

                        Text(text = stringResource(id = R.string.female))
                    }
                }

            }
            var enabled = false
            if (firstName.value.length >= 3 && lastName.value.length >= 3) {
                enabled = true
                Log.d("TTT", "SignUpScreenContent: $enabled")
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(0.9f)
            ) {
                Column(
                    Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    AppButton(
                        text = stringResource(id = R.string.continue_txt),
                        onClick = {
                            if (firstName.value.length >= 3 && lastName.value.length >= 3) {
                                onEventDispatcher.invoke(
                                    UserDataContract.Intent.UpdateInfo(
                                        UpdateInfoRequest(
                                            firstName.value,
                                            lastName.value,
                                            if (gender.value) "1" else "0",
                                            bornDate.value
                                        )
                                    )
                                )
                                nextButtonEnabled.value = false
                            }
                        },
                        enabled = enabled && nextButtonEnabled.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                    )
                }
            }

        }
    }
}
