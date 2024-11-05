package uz.gita.m1nex.core.data.model

internal data class BaseResponse<T>(
    val data: T?,
    val error: ErrorResponse?,
    val message: String?,
)