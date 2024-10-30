package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.paynet.R
import uz.gita.m1nex.paynet.app.screen.addcard.CardNumberVisualTransformation
import uz.gita.m1nex.paynet.app.ui.theme.gray
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight70

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onClickContacts: () -> Unit,
    onClickScan: () -> Unit,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onFocusChanged: (FocusState) -> Unit
) {

    var text by remember { mutableStateOf("") }
    //var isSearchBarFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(gray)
            .padding(top = 2.dp, bottom = 2.dp, start = 12.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.padding(start = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (text.isEmpty()) Text(
                    text = stringResource(id = R.string.card_or_phone),
                    color = textColorLight70,
                    fontSize = 18.sp
                )

                BasicTextField(
                    value = text,
                    visualTransformation = CardNumberVisualTransformation(),
                    onValueChange = {
                        if (it.length == 17) { }
                        else {
                            text = it
                            onValueChange(text)
//                            logger("$it -> $text")
                        }

                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            // isSearchBarFocused = it.isFocused
                            onFocusChanged(it)
                        },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.pnfont_semibold)),
                        fontSize = 18.sp
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (text.isEmpty()) {
                IconButton(onClick = { onClickContacts() }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.ic_contacts),
                        contentDescription = null
                    )
                }

                IconButton(onClick = { onClickScan() }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.ic_scan),
                        contentDescription = null
                    )
                }
            } else {
                IconButton(onClick = { text = "" }) {
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
fun SearchBarPreview2() {
    SearchBar(
        modifier = Modifier,
        onClickContacts = {},
        onClickScan = {},
        onValueChange = {},
        focusRequester = FocusRequester(),
        onFocusChanged = {}
    )
}