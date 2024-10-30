package uz.gita.m1nex.core

import androidx.annotation.Keep
import cafe.adriel.voyager.core.model.ScreenModel
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ScreenModelImpl(val value: KClass<out ScreenModel>)
