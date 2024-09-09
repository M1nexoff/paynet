package uz.gita.m1nex.paynet.app.screen.signup

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.core.util.toRange
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getScreenModel
import cafe.adriel.voyager.hilt.getViewModel
import com.ozcanalasalvar.datepicker.compose.component.SelectorView
import com.ozcanalasalvar.datepicker.compose.datepicker.WheelDatePicker
import com.ozcanalasalvar.datepicker.model.Date
import com.ozcanalasalvar.datepicker.ui.theme.PickerTheme
import com.ozcanalasalvar.datepicker.ui.theme.colorLightPrimary
import com.ozcanalasalvar.datepicker.ui.theme.colorLightTextPrimary
import com.ozcanalasalvar.datepicker.ui.theme.lightPallet
import com.ozcanalasalvar.datepicker.utils.DateUtils
import com.ozcanalasalvar.datepicker.utils.daysOfDate
import com.ozcanalasalvar.datepicker.utils.monthsOfDate
import com.ozcanalasalvar.datepicker.utils.withDay
import com.ozcanalasalvar.datepicker.utils.withMonth
import com.ozcanalasalvar.datepicker.utils.withYear
import com.ozcanalasalvar.wheelview.SelectorOptions
import com.ozcanalasalvar.wheelview.WheelView
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.component.AppButton
import uz.gita.m1nex.paynet.app.ui.theme.component.MaskVisualTransformation
import uz.gita.m1nex.paynet.app.ui.theme.main
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpScreenModelImpl
import java.text.DateFormatSymbols
import java.util.Calendar
import kotlin.math.log
import kotlin.random.Random

class SignUpScreen : Screen {
    @Composable
    override fun Content() {
        Log.d("TTT", "Content: start")
        val viewModel: SignUpContract.Model = hiltScreenModel()
        Log.d("TTT", "Content: viewmodel created")
        SignUpScreenContent(viewModel.collectAsState(), viewModel::onEventDispatcher)
        Log.d("TTT", "Content: end")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpScreenContent(
    state: State<SignUpContract.UiState>,
    eventDispatcher: (SignUpContract.Intent) -> Unit
) {
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val bornDate = remember { mutableStateOf("") }
    val gender = remember { mutableStateOf(false) }
    val nextButtonEnabled = remember { mutableStateOf(true) }
    Box {
        when (state.value) {
            is SignUpContract.UiState.Default -> {}
            is SignUpContract.UiState.Error -> {
                nextButtonEnabled.value = true
            }
            SignUpContract.UiState.Progress -> {
                CircularProgressIndicator(progress = {0.5f},modifier = Modifier.align(Alignment.Center))
            }
        }

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
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
            TextField(
                singleLine = true,
                placeholder = { Text(stringResource(id = R.string.first_name)) },
                value = firstName.value,
                onValueChange = { input ->
                    if (input.length <= 12 && !(input.any { !it.isLetter() })) {
                        firstName.value = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
            TextField(
                singleLine = true,
                placeholder = { Text(stringResource(id = R.string.last_name)) },
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
            if (password.value.length >= 8 && phone.value.length == 9 && firstName.value.length >= 3 && lastName.value.length >= 3){
                enabled = true
                Log.d("TTT", "SignUpScreenContent: $enabled")
            }
            Box(modifier = Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(0.9f)) {
                Column(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .align(Alignment.Center)) {
                    AppButton(
                        text = stringResource(id = R.string.continue_text),
                        onClick = {
                            if (password.value.length >= 8 && phone.value.length == 9 && firstName.value.length >= 3 && lastName.value.length >= 3) {
                                eventDispatcher.invoke(
                                    SignUpContract.Intent.SignUp(
                                        SignUpRequest(
                                            "+998" + phone.value,
                                            password.value,
                                            firstName.value,
                                            lastName.value,
                                            bornDate.value,
                                            if (gender.value) "1" else "0"
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

                    Text(
                        text = stringResource(id = R.string.sign_in_text),
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .clickable(onClick = { eventDispatcher.invoke(SignUpContract.Intent.SignIn) }),
                        textAlign = TextAlign.Center
                    )

                }
            }
        }

    }
}

@Composable
fun WheelDatePicker(
    backgroundColor: Color = Color.White,
    offset: Int = 4,
    yearsRange: IntRange = IntRange(1923, 2121),
    startDate: Date = Date(DateUtils.getCurrentTime()),
    textSize: Int = 16,
    selectorEffectEnabled: Boolean = true,
    onDateChanged: (Int, Int, Int, Long) -> Unit = { _, _, _, _ -> },
    darkModeEnabled: Boolean = true,
) {

    var selectedDate by remember { mutableStateOf(startDate) }

    val months = selectedDate.monthsOfDate()

    val days = selectedDate.daysOfDate()

    val years = mutableListOf<Int>().apply {
        for (year in yearsRange) {
            add(year)
        }
    }

    LaunchedEffect(selectedDate) {
        onDateChanged(selectedDate.day, selectedDate.month, selectedDate.year, selectedDate.date)
    }

    val fontSize = maxOf(13, minOf(19, textSize))


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {

        val height = (fontSize + 11).dp


        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp)
        ) {


            key(days.size) {
                WheelView(modifier = Modifier.weight(1f),
                    itemSize = DpSize(150.dp, height),
                    selection = maxOf(days.indexOf(selectedDate.day), 0),
                    itemCount = days.size,
                    rowOffset = offset,
                    selectorOption = SelectorOptions().copy(selectEffectEnabled = selectorEffectEnabled, enabled = false),
                    onFocusItem = {
                        selectedDate = selectedDate.withDay(days[it])
                    },
                    content = {
                        Text(
                            text = days[it].toString(),
                            textAlign = TextAlign.End,
                            modifier = Modifier.width(50.dp),
                            fontSize = fontSize.sp,
                            color = if (darkModeEnabled) PickerTheme.colors.textPrimary else colorLightTextPrimary
                        )
                    })
            }

            WheelView(modifier = Modifier.weight(2f),
                itemSize = DpSize(150.dp, height),
                selection = maxOf(months.indexOf(selectedDate.month), 0),
                itemCount = months.size,
                rowOffset = offset,
                selectorOption = SelectorOptions().copy(selectEffectEnabled = selectorEffectEnabled, enabled = false),
                onFocusItem = {
                    selectedDate = selectedDate.withMonth(months[it])
                },
                content = {
                    Text(
                        text = DateFormatSymbols().months[months[it]],
                        textAlign = TextAlign.Start,
                        modifier = Modifier.width(120.dp),
                        fontSize = fontSize.sp,
                        color = if (darkModeEnabled) PickerTheme.colors.textPrimary else colorLightTextPrimary
                    )
                })


            WheelView(modifier = Modifier.weight(1f),
                itemSize = DpSize(150.dp, height),
                selection = years.indexOf(selectedDate.year),
                itemCount = years.size,
                rowOffset = offset,
                isEndless = years.size > offset * 2,
                selectorOption = SelectorOptions().copy(selectEffectEnabled = selectorEffectEnabled, enabled = false),
                onFocusItem = {
                    selectedDate = selectedDate.withYear(years[it])
                },
                content = {
                    Text(
                        text = years[it].toString(),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.width(100.dp),
                        fontSize = fontSize.sp,
                        color = if (darkModeEnabled) PickerTheme.colors.textPrimary else colorLightTextPrimary
                    )
                })
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = if (darkModeEnabled) PickerTheme.pallets else lightPallet
                    )
                ),
        ) {}

        SelectorView(darkModeEnabled= darkModeEnabled, offset = offset)



    }


}

@Preview
@Composable
fun SignUpScreenContentPreview() {
    val state = remember { mutableStateOf<SignUpContract.UiState>(SignUpContract.UiState.Default(SignUpRequest("", "", "", "", "", ""))) }
    SignUpScreenContent(
        state = state, eventDispatcher = {}
    )
}