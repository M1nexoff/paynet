package uz.gita.m1nex.entity.data.model.respone

import com.google.gson.annotations.SerializedName

data class TotalBalanceResponse(
    @SerializedName("total-balance")
    val totalBalance: Int
)

data class BasicInfoResponse(
    @SerializedName("firsrt-name")
    val firstName: String,
    @SerializedName("gender-type")
    val genderType: Int,
    val age: Int
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

data class LastTransfersResponse(
    val type: String,
    val from: String,
    val to: String,
    val amount: Int,
    val time: Long
)

data class UpdateInfoResponse(
    val message: String
)
