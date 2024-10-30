package uz.gita.m1nex.usecase.transfer

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.sign.SignUp

interface TransferUseCase {
    fun transfer(receiverPan: String,type: String, amount: Long,senderId: String): Flow<ResultData<Unit>>
    fun transferVerify(code: String): Flow<ResultData<Unit>>
    fun getUserData(pan: String): Flow<ResultData<MessageData.Text>>
    fun transferResend(): Flow<ResultData<Unit>>
}