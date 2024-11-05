package uz.gita.m1nex.core.data.model

import com.google.gson.annotations.SerializedName

data class UpdateInfoRequest(
    @SerializedName("first-name") val firstName: String,
    @SerializedName("last-name") val lastName: String,
    @SerializedName("gender-type") val genderType: String,
    @SerializedName("born-date") val bornDate: String
)
data class FullInfoResponse(
    @SerializedName("born-date")
    val bornDate: Long,
    @SerializedName("first-name")
    val firstName: String,
    val gender: Int,
    @SerializedName("last-name")
    val lastName: String,
    val phone: String
)
