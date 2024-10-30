package uz.gita.m1nex.core.data.model.card

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CardData(
    val id: String,
    val name: String,
    val amount: Long,
    val owner: String,
    val pan: String,
    @SerializedName("expired-year")
    val expiredYear: Int,
    @SerializedName("expired-month")
    val expiredMonth: Int,
    @SerializedName("theme-type")
    val themeType: Int,
    @SerializedName("is-visible")
    val isVisible: Boolean,
):Serializable
