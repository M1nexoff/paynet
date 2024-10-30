package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.paynet.R
//import uz.gita.m1nex.paynet.app.ui.theme.component.TextNormal
import uz.gita.m1nex.paynet.app.ui.theme.textColor
import uz.gita.m1nex.paynet.app.ui.theme.textColorLight

@Composable
fun P2PMoneyInputWithTitle(
    modifier: Modifier = Modifier,
    onError: (Boolean) -> Unit,
    focusRequester: FocusRequester,
    onValueChange: (String) -> Unit
) {

    var value by remember { mutableStateOf("") }
    var isValueInputFocused by remember { mutableStateOf(true) }

    Row(
        modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFFE9EAED))
            .padding(vertical = 4.dp, horizontal = 18.dp)
            .focusRequester(focusRequester)
            .focusable(true),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(1f)) {
            Text(text = stringResource(id = R.string.you_are_transfering), color = textColorLight)

            BasicTextField(
                value = value,
                onValueChange = {
                    value = it.trim()
                    onValueChange(value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { isValueInputFocused = it.isFocused },
                keyboardActions = KeyboardActions(onDone = { focusRequester.freeFocus() }),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(color = textColor, fontSize = 24.sp),
                singleLine = true,
            )
        }

        if (value.isNotEmpty() && isValueInputFocused) {
            Icon(
                painter = painterResource(id = R.drawable.search_clear),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(24.dp)
                    .clickable { value = "" },
                tint = textColorLight
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    P2PMoneyInputWithTitle(
        onError = {},
        modifier = Modifier
            .fillMaxWidth(),
        focusRequester = remember { FocusRequester() },
        onValueChange = {}
    )
}

private fun String.formatter(): String {
    val s = StringBuilder(this.reversed())
    s.forEachIndexed { index, _ ->
        if (index % 4 == 0) s.insert(index, " ")
    }
    return s.reversed().toString()
}