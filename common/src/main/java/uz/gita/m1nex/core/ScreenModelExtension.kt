package uz.gita.m1nex.core

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.ScreenModelEntryPoint
import cafe.adriel.voyager.hilt.ScreenModelFactory
import dagger.hilt.android.EntryPointAccessors
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

@Keep
@Composable
public inline fun <reified T : ScreenModel> Screen.hiltScreenModel(
    tag: String? = null
): T {
    val context = LocalContext.current
    return rememberScreenModel(tag) {
        val screenModels = EntryPointAccessors
            .fromActivity(context.componentActivity, ScreenModelEntryPoint::class.java)
            .screenModels()
        val impl = T::class.java.getDeclaredAnnotation(ScreenModelImpl::class.java) ?: error("Annotation ScreenModelImpl not found")

        val model = screenModels[impl.value.java]?.get()
            ?: error(
                "${impl.value.java.canonicalName} not found in hilt graph.\nPlease, check if you have a Multibinding " +
                        "declaration to your ScreenModel using @IntoMap and " +
                        "@ScreenModelKey(${T::class.qualifiedName}::class)"
            )
        model as T
    }
}

@Keep
@Composable
public inline fun <reified T : ScreenModel, reified F : ScreenModelFactory> Screen.hiltScreenModel(
    tag: String? = null,
    noinline factory: (F) -> T
): T {
    val context = LocalContext.current
    return rememberScreenModel(tag) {
        val screenFactories = EntryPointAccessors
            .fromActivity(context.componentActivity, ScreenModelEntryPoint::class.java)
            .screenModelFactories()

        val impl = T::class.java.getDeclaredAnnotation(ScreenModelImpl::class.java) ?: error("Annotation ScreenModelImpl not found")
        val f = impl.value.java.classes.first { it == F::class.java }

        val screenFactory = screenFactories[f]?.get()

            ?: error(
                "${F::class.java} not found in hilt graph.\nPlease, check if you have a Multibinding " +
                        "declaration to your ScreenModelFactory using @IntoMap and " +
                        "@ScreenModelFactoryKey(${F::class.qualifiedName}::class)"
            )
        factory.invoke(screenFactory as F)
    }
}

@Keep
internal inline fun <reified T> findOwner(context: Context): T? {
    var innerContext = context
    while (innerContext is ContextWrapper) {
        if (innerContext is T) {
            return innerContext
        }
        innerContext = innerContext.baseContext
    }
    return null
}

@PublishedApi
internal val Context.componentActivity: ComponentActivity
    get() = findOwner<ComponentActivity>(this)
        ?: error("Context must be a androidx.activity.ComponentActivity. Current is $this")

@PublishedApi
internal val Context.defaultViewModelProviderFactory: ViewModelProvider.Factory
    get() = componentActivity.defaultViewModelProviderFactory

@Composable
fun <T> previewStateOf(value: T) : State<T> = remember { mutableStateOf(value) }

@SuppressLint("SuspiciousModifierThen")
fun Modifier.angledGradientBackground(colors: List<Color>, degrees: Float) = this.then(
    drawBehind {
        val (x, y) = size
        val gamma = atan2(y, x)

        if (gamma == 0f || gamma == (PI / 2).toFloat()) {
            // degenerate rectangle
            return@drawBehind
        }

        val degreesNormalised = (degrees % 360).let { if (it < 0) it + 360 else it }

        val alpha = (degreesNormalised * PI / 180).toFloat()

        val gradientLength = when (alpha) {
            // ray from centre cuts the right edge of the rectangle
            in 0f..gamma, in (2 * PI - gamma)..2 * PI -> {
                x / cos(alpha)
            }
            // ray from centre cuts the top edge of the rectangle
            in gamma..(PI - gamma).toFloat() -> {
                y / sin(alpha)
            }
            // ray from centre cuts the left edge of the rectangle
            in (PI - gamma)..(PI + gamma) -> {
                x / -cos(alpha)
            }
            // ray from centre cuts the bottom edge of the rectangle
            in (PI + gamma)..(2 * PI - gamma) -> {
                y / -sin(alpha)
            }
            // default case (which shouldn't really happen)
            else -> hypot(x, y)
        }

        val centerOffsetX = cos(alpha) * gradientLength / 2
        val centerOffsetY = sin(alpha) * gradientLength / 2

        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                // negative here so that 0 degrees is left -> right
                //and 90 degrees is top -> bottom
                start = Offset(center.x - centerOffsetX, center.y - centerOffsetY),
                end = Offset(center.x + centerOffsetX, center.y + centerOffsetY)
            ),
            size = size
        )
    }
)
fun getGradient(type: Int): Brush = when (type) {
    0 -> {
        Brush.verticalGradient(listOf(Color(0xFF0063B5), Color(0xFF00EBC8)))
    }

    1 -> {
        Brush.verticalGradient(listOf(Color(0xFF06693a), Color(0xFF20d970)))
    }

    2 -> {
        Brush.verticalGradient(listOf(Color(0xFF5b0a8a), Color(0xFFa9518d)))
    }

    3 -> {
        Brush.verticalGradient(listOf(Color(0xFF930709), Color(0xFFff9c63)))
    }

    4 -> {
        Brush.verticalGradient(listOf(Color(0xFF886e33), Color(0xFFffd645)))
    }

    5 -> {
        Brush.verticalGradient(listOf(Color(0xFF282a75), Color(0xFF009ffd)))
    }

    6 -> {
        Brush.verticalGradient(listOf(Color(0xFF191a1f), Color(0xFF55555f)))
    }

    7 -> {
        Brush.verticalGradient(listOf(Color(0xFF6c0f17), Color(0xFFbd1373)))
    }

    else -> {
        Brush.verticalGradient(listOf(Color(0xFFa95403), Color(0xFFecbe38)))
    }
}
