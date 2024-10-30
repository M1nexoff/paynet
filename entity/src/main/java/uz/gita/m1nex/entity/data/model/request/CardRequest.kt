package uz.gita.m1nex.entity.data.model.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class AddCardRequest(
    val pan: String,
    @SerializedName("expired-year")
    val expiredYear: String,
    @SerializedName("expired-month")
    val expiredMonth: String,
    val name: String
)
@Keep
data class UpdateCardRequest(
    val id: Long,
    val name: String,
    @SerializedName("theme-type")
    val themeType: Int,
    @SerializedName("is-visible")
    val isVisible: Boolean
)