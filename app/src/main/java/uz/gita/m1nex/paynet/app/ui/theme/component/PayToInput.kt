package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.ui.theme.gray
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight80

@Composable
fun PayToInput(
    modifier: Modifier = Modifier,
    onClickContacts: () -> Unit,
    onClickScan: () -> Unit,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onFocusChanged: (FocusState) -> Unit
) {
    val empty = "0 so'm"
    var textVisible by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(if (!textVisible) empty else "") }
    val focusManager = LocalFocusManager.current

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(gray)
            .height(56.dp)
            .padding(top = 2.dp, bottom = 2.dp, start = 12.dp)
    ) {
        Row(
            modifier = Modifier, verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.padding(start = 4.dp), contentAlignment = Alignment.CenterStart
            ) {
                if (text.isEmpty())
                    Text(
                        text = stringResource(id = R.string.placeholder_pay_to),
                        color = textColorLight80,
                        fontSize = 18.sp
                    )
                BasicTextField(value = text,
                    onValueChange = {
                       if (it.length > 8) {
                         // big logic
                       }
                       else {
                           text = it
                           onValueChange(text)
                       }
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        },
                    ),
                    visualTransformation = MoneyVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            if (it.isFocused && text == empty) {
                                text = ""
                            }
                            else if (text.isEmpty()) {
                                text = empty
                            }
//                            "hasFocus=${it.hasFocus} isFocused=${it.isFocused}".myLog()
                            textVisible = it.isFocused
                            onFocusChanged(it)
                        },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                        fontSize = 18.sp
                    ),

                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (textVisible) {
                IconButton(onClick = {
                    text = ""
                    focusManager.clearFocus()
                }
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        painter = painterResource(id = R.drawable.search_clear),
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun SearchBarPreview() {
    var searchText by remember { mutableStateOf("") }
    var isSearchingStateActive by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var isSearchBarFocused by remember { mutableStateOf(false) }
    PayToInput(
        modifier = Modifier.padding(horizontal = 12.dp),
        onClickContacts = {},
        onClickScan = {},
        onValueChange = {
            if (it.length == 16) {
                searchText = it
            } else {
                searchText = it
            }
        },
        focusRequester = focusRequester,
        onFocusChanged = {
            isSearchBarFocused = it.isFocused
            if (isSearchBarFocused) isSearchingStateActive = true
        })


}