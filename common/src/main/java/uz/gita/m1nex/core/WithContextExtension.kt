package uz.gita.m1nex.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T> withContextSafety(context: CoroutineContext, block: suspend CoroutineScope.() -> T) = withContext(context, block)