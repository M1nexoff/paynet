package uz.gita.m1nex.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

fun <T> flowWithCatch(block: suspend FlowCollector<ResultData<T>>.() -> Unit): Flow<ResultData<T>> = flow<ResultData<T>> {
    block(this)
}.catch { emit(ResultData.fail("Error:$it")) }