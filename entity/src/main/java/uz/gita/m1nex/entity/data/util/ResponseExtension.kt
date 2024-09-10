package uz.gita.m1nex.entity.data.util

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import retrofit2.Response
import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.asFail
import uz.gita.m1nex.core.asSuccess
import uz.gita.m1nex.core.fail
import uz.gita.m1nex.core.isSuccess
import uz.gita.m1nex.core.success
import uz.gita.m1nex.entity.R
import uz.gita.m1nex.entity.data.model.respone.BaseResponse

internal fun <T> Response<BaseResponse<T>>.toResultData(): ResultData<T> {
    val code = code()
    val body = body()
    return when{
        code in 200..299 -> ResultData.success(body()!!.data!!)
        code in 500..599 -> ResultData.fail("Internal server error: ${message()}")
        body?.message != null -> ResultData.fail(body.message)
        body?.error?.message != null -> ResultData.fail(body.error.message)
        else -> ResultData.fail("Error from server: code=${code}")
    }
}

fun <T> Response<T>.toResultData(errorHandler: ((String) -> String)? = null): ResultData<T> = when (code()) {
    in 200..299 -> ResultData.success(body()!!)
    in 400..499 -> {
        val errorMessage = parseErrorMessage(errorBody()?.string())
        ResultData.fail(errorMessage)
    }
    in 500..599 -> ResultData.fail(getErrorMessage(errorHandler))
    else -> ResultData.fail(getErrorMessage(errorHandler))
}

private val gson = Gson()

private fun <T> Response<T>.getErrorMessage(errorHandler: ((String) -> String)?): MessageData = when {
    errorBody() != null -> MessageData.Text(
        if (errorHandler != null) errorHandler(errorBody()!!.string())
        else gson.fromJson(errorBody()?.string().toString(), BaseResponse::class.java).error?.message.toString()
    )
    else -> MessageData.Resource(R.string.error)
}

fun <T, R> ResultData<T>.mapTo(mapper: (T) -> R): ResultData<R> = when {
    isSuccess -> ResultData.success(mapper(asSuccess.data))
    else -> asFail
}

fun parseErrorMessage(json: String?): String {
    return try {
        val gson = Gson()
        val jsonObject = gson.fromJson(json, JsonObject::class.java)
        jsonObject.get("message")?.asString ?: "Unknown error"
    } catch (e: JsonSyntaxException) {
        "Error parsing error message"
    } catch (e: Exception) {
        "Unknown error occurred"
    }
}
