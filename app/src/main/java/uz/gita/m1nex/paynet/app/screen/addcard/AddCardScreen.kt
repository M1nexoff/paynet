package uz.gita.m1nex.paynet.app.screen.addcard

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.m1nex.core.getText
import uz.gita.m1nex.core.hiltScreenModel
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundLight
import uz.gita.m1nex.paynet.app.ui.theme.BackgroundWhite
import uz.gita.m1nex.paynet.app.ui.theme.Typography
import uz.gita.m1nex.paynet.app.ui.theme.errorColorX
import uz.gita.m1nex.paynet.app.ui.theme.gray
import uz.gita.m1nex.paynet.app.ui.theme.leftColor
import uz.gita.m1nex.paynet.app.ui.theme.pinGreen
import uz.gita.m1nex.paynet.app.ui.theme.rightColor
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight90
import uz.gita.m1nex.paynet.app.ui.theme.textColorX
import uz.gita.m1nex.presenter.screenmodel.addcard.AddCardContract

class AddCardScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: AddCardContract.Model = hiltScreenModel()
        val state = screenModel.collectAsState()

        AddCardScreenContent(state, screenModel::onEventDispatcher)
    }
}

@Composable
fun AddCardScreenContent(
    state: State<AddCardContract.UIState>,
    onEventDispatcher: (AddCardContract.Intent) -> Unit
) {
    var error by remember { mutableStateOf<String?>(null) }
    error = when (state.value) {
        is AddCardContract.UIState.Error -> {
            (state.value as AddCardContract.UIState.Error).message.getText()
        }
        AddCardContract.UIState.InitStat -> {
            null
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        TopSection(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = R.string.add_card,
            clickBack = {
                onEventDispatcher.invoke(AddCardContract.Intent.Back)
            })
        var phoneSt by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current
        var inputText by remember {
            mutableStateOf("")
        }
        val isError by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            leftColor,
                            rightColor
                        ),
                    )
                )
        ) {
            AddCardComponent(
                text = phoneSt,
                leadingIcon = {
                    androidx.compose.material.Text(
                        "",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                modifier = Modifier.padding(top = 16.dp),
                onTextChanged = {
                    if (it.length <= 16) {
                        phoneSt = it
                    } else focusManager.clearFocus()
                },
                enabled = true,
                isError = false,
                error = ""
            )

            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp, top = 24.dp)
                    .width(100.dp)
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(gray)
                    .border(
                        width = 1.dp,
                        color = if (isError) errorColorX else gray,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.CenterStart
            ) {

                ExpiredComponent(
                    text = inputText,
                    leadingIcon = {
                        androidx.compose.material.Text(
                            "",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    },
                    modifier = Modifier.padding(),
                    onTextChanged = {
                        inputText = it
                        if (it.length < 4) {
                            //
                        } else focusManager.clearFocus()
                    },
                    enabled = true,
                    isError = false,
                    error = ""
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        error?.let {
            Text(
                text = error!!,
                color = Color.Red
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(BackgroundWhite)
        ) {
            Button(
                enabled = phoneSt.length == 16 && inputText.length == 4,
                onClick = {
                    onEventDispatcher.invoke(
                        AddCardContract.Intent.Continue(
                            phoneSt,
                            "20".plus(inputText.substring(2, 4)),
                            inputText.substring(0, 2),
                        )
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(pinGreen)
                    .clickable {

                    }
            ) {
                Text(
                    text = stringResource(id = R.string.continue_txt),
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pnfont_regular)),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun TopSection(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    clickBack: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(top = 16.dp)
            .wrapContentSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_navigation_arrow_left_x24),
            contentDescription = null,
            modifier = Modifier
                .padding()
                .size(24.dp)
                .clickable { clickBack.invoke() }
        )

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(id = text),
            color = textColorLight90,
            fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
            fontSize = 22.sp
        )
    }
}

@Composable
fun ExpiredComponent(
    text: String = "",
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    error: String? = null,
    onTextChanged: (value: String) -> Unit = {}
) {
    Column(modifier = modifier) {

        BasicTextField(
            value = text,
            onValueChange = {
                if (it.length <= 4) {
                    onTextChanged(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            enabled, readOnly,
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                color = Color.Black, // Change the text color if needed
                fontSize = 18.sp, // Change the font size
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)) // Change the font family
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = ExpiredMaskTransformation,
            cursorBrush = SolidColor(textColorX),
            decorationBox = { innerTextField ->

                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(gray)
                        .border(
                            width = 1.dp,
                            color = if (isError) errorColorX else gray,
                            shape = RoundedCornerShape(8.dp)
                        ),
                ) {
                    Row(modifier = Modifier.padding()) {
                        leadingIcon?.invoke()
                        Box(
                            modifier = Modifier
                                .padding(
                                )
                                .align(Alignment.CenterVertically)
                        ) {
                            innerTextField()
                        }
                    }
                }

            }
        )
        if (isError) {
            androidx.compose.material.Text(
                error ?: stringResource(id = R.string.unknown_error),
                style = Typography.headlineLarge
            )
        }
    }
}

@Composable
fun AddCardComponent(
    text: String = "",
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    error: String? = null,
    onTextChanged: (value: String) -> Unit = {}
) {
    Column(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                if (it.length <= 16) {
                    onTextChanged(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            enabled, readOnly,
            singleLine = true,
            maxLines = 1,

            visualTransformation = CardNumberVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            cursorBrush = SolidColor(textColorX),
            textStyle = TextStyle(
                color = Color.Black, // Change the text color if needed
                fontSize = 18.sp, // Change the font size
                fontFamily = FontFamily(Font(R.font.pnfont_semibold)) // Change the font family
            ),
            decorationBox = { innerTextField ->

                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(gray)
                        .border(
                            width = 1.dp,
                            color = if (isError) errorColorX else gray,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Row(modifier = Modifier.padding()) {
                        leadingIcon?.invoke()
                        Box(
                            modifier = Modifier
                                .padding(
                                    start = 8.dp,
                                    end = 8.dp

                                )
                                .align(Alignment.CenterVertically)
                        ) {
                            if (text.isEmpty()) {
                                androidx.compose.material.Text(
                                    "Karta yoki telefon",
                                    color = Color.Gray,
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                                )
                            }
                            innerTextField()
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.ic_contacts),
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .align(Alignment.CenterVertically),
                            contentDescription = null
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_scan),
                            modifier = Modifier
                                .padding(start = 8.dp, end = 16.dp)
                                .align(Alignment.CenterVertically),
                            contentDescription = null
                        )
                    }
                }

            }
        )
        if (isError) {
            androidx.compose.material.Text(
                error ?: stringResource(id = R.string.unknown_error),
                style = Typography.headlineLarge
            )
        }
    }
}

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Keep only digits
        val digits = text.text.filter { it.isDigit() }
        val stringBuilder = StringBuilder()

        // Loop through the digits and insert spaces after every 4th digit
        digits.forEachIndexed { index, c ->
            stringBuilder.append(c)
            // Add space after every 4 digits, but not at the end
            if (index % 4 == 3 && index != digits.lastIndex) {
                stringBuilder.append(' ')
            }
        }

        val newText = stringBuilder.toString()

        // Create an offset mapping to correlate original text indexing with transformed text
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset > digits.length) return newText.length

                val spacesBeforeOffset = (offset - 1) / 4
                return offset + spacesBeforeOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset > newText.length) return digits.length

                val spacesBeforeOffset = (offset - 1) / 5
                return offset - spacesBeforeOffset
            }
        }

        return TransformedText(AnnotatedString(newText), offsetMapping)
    }
}

object ExpiredMaskTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilter(text)
    }

    private fun maskFilter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 9) text.text.substring(0..8) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (listOf(1, 4, 6).contains(i)) out += "/"
        }

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 5) return offset + 1
                if (offset <= 7) return offset + 2
                if (offset <= 9) return offset + 3
                return 12
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 7) return offset - 1
                if (offset <= 10) return offset - 2
                if (offset <= 12) return offset - 3
                return 9
            }
        }

        return TransformedText(AnnotatedString(out), numberOffsetTranslator)
    }
}
