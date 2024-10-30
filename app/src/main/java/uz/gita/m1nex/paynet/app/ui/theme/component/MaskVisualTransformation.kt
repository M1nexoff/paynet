package uz.gita.m1nex.paynet.app.ui.theme.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue

class MaskVisualTransformation(private val mask: String) : VisualTransformation {

    private val specialSymbolsIndices = mask.indices.filter { mask[it] != '#' }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == '#' }
        }
    }
}

class MoneyVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Keep only digits
        val digits = text.text.filter {
            it.isLetterOrDigit()
        }
        val stringBuilder = StringBuilder()

        // Insert spaces after every 3 digits from the right
        var counter = 0
        for (i in digits.length - 1 downTo 0) {
            stringBuilder.append(digits[i])
            counter++
            if (counter == 3 && i != 0) {
                stringBuilder.append(' ')
                counter = 0
            }
        }
        stringBuilder.reverse()

        val newText = stringBuilder.toString()

        // Create an offset mapping to correlate original text indexing with transformed text
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset > digits.length) return newText.length

                var transformedOffset = offset
                var spaces = 0

                for (i in digits.indices) {
                    if (i == transformedOffset) break
                    if ((digits.length - i) % 3 == 0 && i != 0) {
                        spaces++
                    }
                }

                return transformedOffset + spaces
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 0) return 0
                if (offset > newText.length) return digits.length

                var originalOffset = offset
                var spaces = 0

                for (i in newText.indices) {
                    if (i == originalOffset) break
                    if (newText[i] == ' ') {
                        spaces++
                    }
                }

                return originalOffset - spaces
            }
        }

        return TransformedText(AnnotatedString(newText), offsetMapping)
    }
}
