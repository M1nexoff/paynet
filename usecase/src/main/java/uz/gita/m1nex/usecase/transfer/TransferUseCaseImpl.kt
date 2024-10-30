package uz.gita.m1nex.usecase.transfer

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.flowWithCatch
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.entity.repository.TransferRepository
import javax.inject.Inject

internal class TransferUseCaseImpl @Inject constructor(
    private val transferRepository: TransferRepository
) : TransferUseCase {
    override fun transfer(receiverPan: String,type: String, amount: Long,senderId: String): Flow<ResultData<Unit>> = flowWithCatch {
        val result = transferRepository.transfer(receiverPan = receiverPan,type = type,amount = amount,senderId = senderId)
        emit(result)
    }

    override fun transferVerify(code: String): Flow<ResultData<Unit>> = flowWithCatch{
        val result = transferRepository.transferVerify(code)
        emit(result)
    }

    override fun getUserData(pan: String): Flow<ResultData<MessageData.Text>> = flowWithCatch{
        val result = transferRepository.getUserData(pan)
        emit(result)
    }

    override fun transferResend(): Flow<ResultData<Unit>> = flowWithCatch{
        val result = transferRepository.transferResend()
        emit(result)
    }
}