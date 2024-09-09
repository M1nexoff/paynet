package uz.gita.m1nex.paynet.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import uz.gita.m1nex.paynet.R

// Define your custom fonts
val main = FontFamily(
    Font(R.font.pnfont_medium, FontWeight.Medium),
    Font(R.font.pnfont_semibold, FontWeight.SemiBold),
    Font(R.font.pnfont_regular, FontWeight.Normal),

)
val carosSoft = FontFamily(Font(R.font.caros_soft_extra_bold, FontWeight.Bold))



// Customize the typography
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = main,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = main,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = main,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,

        letterSpacing = 0.5.sp

    )
)
