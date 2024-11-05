package uz.gita.m1nex.core.data.model

import com.google.gson.annotations.SerializedName
import uz.gita.m1nex.core.data.model.Child
import uz.gita.m1nex.core.data.model.transfer.TransferData

internal sealed interface TransferResponses {

    data class GetCardOwnerByPan(
        val pan: String
    ) : TransferResponses

    data class GetFee(
        val fee: Int,
        val amount: Long
    ) : TransferResponses

    data class TransferResponse(
        val token: String
    ) : TransferResponses

    data class TransferVerify(
        val message: String
    ) : TransferResponses

    data class GetHistory(
        @SerializedName("child")
        val child: List<Child>,
        @SerializedName("current-page")
        val currentPage: Int,
        @SerializedName("total-elements")
        val totalElements: Int,
        @SerializedName("total-pages")
        val totalPages: Int
    ) : TransferResponses

    data class TransferResend(
        val token: String
    ) : TransferResponses


}