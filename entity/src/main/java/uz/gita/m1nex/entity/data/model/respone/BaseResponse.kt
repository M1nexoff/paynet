package uz.gita.m1nex.entity.data.model.respone

internal data class BaseResponse<T>(
    val data: T?,
    val error: ErrorResponse?,
    val message: String?,
)