package uz.gita.m1nex.entity.repository.impl

import kotlinx.coroutines.Dispatchers
import uz.gita.m1nex.core.MessageData
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.sign.SignIn
import uz.gita.m1nex.core.data.model.sign.SignUp
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.core.withContextSafety
import uz.gita.m1nex.entity.data.local.LocalStorage
import uz.gita.m1nex.entity.data.model.request.SignInVerifyRequest
import uz.gita.m1nex.entity.data.model.request.SignUpVerifyRequest
import uz.gita.m1nex.entity.data.model.request.TokenRequest
import uz.gita.m1nex.entity.data.model.request.TransferRequests
import uz.gita.m1nex.entity.data.model.request.UpdateTokenRequest
import uz.gita.m1nex.entity.data.remote.AuthApi
import uz.gita.m1nex.entity.data.remote.TransferApi
import uz.gita.m1nex.entity.data.util.mapTo
import uz.gita.m1nex.entity.data.util.toRequest
import uz.gita.m1nex.entity.data.util.toResultData
import uz.gita.m1nex.entity.repository.AuthRepository
import uz.gita.m1nex.entity.repository.TransferRepository
import javax.inject.Inject

internal class TransferRepositoryImpl @Inject constructor(
    private val localStorage: LocalStorage,
    private val transferApi: TransferApi
) : TransferRepository {

    override suspend fun transfer(
        receiverPan: String,
        type: String,
        amount: Long,
        senderId: String
    ): ResultData<Unit> = withContextSafety(Dispatchers.IO){
        transferApi.transfer(TransferRequests.TransferRequest(type,senderId,receiverPan,amount))
            .toResultData()
            .mapTo {
                localStorage.transferToken = it.token
            }
    }

    override suspend fun transferVerify(code: String): ResultData<Unit> = withContextSafety(Dispatchers.IO) {
        transferApi.transferVerify(TransferRequests.TransferVerify(localStorage.transferToken, code))
            .toResultData()
            .mapTo {
                localStorage.transferToken = ""

            }
    }

    override suspend fun getUserData(pan: String): ResultData<MessageData.Text> = withContextSafety(Dispatchers.IO) {
        transferApi.getCardOwnerByPan(TransferRequests.GetCardOwnerByPan(pan))
            .toResultData()
            .mapTo {
                MessageData.Text(it.pan)
            }
    }

    override suspend fun transferResend(): ResultData<Unit> = withContextSafety(Dispatchers.IO) {
        transferApi.transferResend(TransferRequests.TransferResend(localStorage.transferToken))
            .toResultData()
            .mapTo {
                localStorage.transferToken = ""
            }
    }

}