package uz.gita.m1nex.entity.data.model.request

import com.google.gson.annotations.SerializedName

internal sealed interface TransferRequests {

    data class GetCardOwnerByPan(val pan: String) : TransferRequests

    data class GetFee(
        @SerializedName("sender-id")
        val senderId: String,
        val receiver: String,
        val amount: Long
    ) : TransferRequests

    data class TransferRequest(
        val type: String,
        @SerializedName("sender-id")
        val senderId: String,
        @SerializedName("receiver-pan")
        val receiverPan: String,
        val amount: Long
    ) : TransferRequests

    data class TransferVerify(
        val token: String,
        val code: String
    ) : TransferRequests

    data class TransferResend(
        val token: String
    ) : TransferRequests

}