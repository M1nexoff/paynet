package uz.gita.m1nex.entity.repository

import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ResultData

interface TransferRepository {

    suspend fun transfer(receiverPan: String,type: String, amount: Long,senderId: String): ResultData<Unit>
    suspend fun transferVerify(code: String): ResultData<Unit>
    suspend fun getUserData(pan: String): ResultData<MessageData.Text>
    suspend fun transferResend(): ResultData<Unit>

}