package uz.gita.m1nex.entity.data.model.request

import com.google.gson.annotations.SerializedName

data class UpdateInfoRequest(
    @SerializedName("first-name") val firstName: String,
    @SerializedName("last-name") val lastName: String,
    @SerializedName("gender-type") val genderType: String,
    @SerializedName("born-date") val bornDate: String
)
