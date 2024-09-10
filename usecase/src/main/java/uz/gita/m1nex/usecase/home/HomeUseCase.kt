package uz.gita.m1nex.usecase.home

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.entity.data.model.request.UpdateInfoRequest
import uz.gita.m1nex.entity.data.model.respone.BasicInfoResponse
import uz.gita.m1nex.entity.data.model.respone.FullInfoResponse
import uz.gita.m1nex.entity.data.model.respone.LastTransfersResponse

interface HomeUseCase {
    fun getTotalBalance(): Flow<ResultData<Int>>
    fun getBasicUserInfo(): Flow<ResultData<BalanceAndName>>
    fun getFullUserInfo(): Flow<ResultData<FullInfoResponse>>
    fun getLastTransfers(): Flow<ResultData<List<LastTransfersResponse>>>
    fun updateUserInfo(updateUserInfoRequest: UpdateInfoRequest): Flow<ResultData<Unit>>
}