package uz.gita.m1nex.usecase.home

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.BasicInfo
import uz.gita.m1nex.core.data.model.UpdateInfoRequest
import uz.gita.m1nex.core.data.model.FullInfoResponse
import uz.gita.m1nex.entity.data.model.respone.LastTransfersResponse

interface HomeUseCase {
    fun getTotalBalance(): Flow<ResultData<Int>>
    fun getPhone(): Flow<ResultData<String>>
    fun getBasicUserInfo(): Flow<ResultData<BasicInfo>>
    fun getFullUserInfo(): Flow<ResultData<FullInfoResponse>>
    fun getLastTransfers(): Flow<ResultData<List<LastTransfersResponse>>>
    fun updateUserInfo(updateUserInfoRequest: UpdateInfoRequest): Flow<ResultData<Unit>>
    fun isVerified(): Flow<ResultData<Boolean>>
    fun setVerified(): Flow<ResultData<Unit>>
}