package uz.gita.m1nex.core.data.model

import com.google.gson.annotations.SerializedName

data class Child(
    @SerializedName("amount") val amount: Int,
    @SerializedName("from") val from: String,
    @SerializedName("time") val time: Long,
    @SerializedName("to") val to: String,
    @SerializedName("type") val type: String
)