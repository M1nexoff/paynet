package uz.gita.m1nex.entity.repository

import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.BasicInfo
import uz.gita.m1nex.core.data.model.UpdateInfoRequest
import uz.gita.m1nex.core.data.model.FullInfoResponse
import uz.gita.m1nex.entity.data.model.respone.LastTransfersResponse

interface HomeRepository {
    suspend fun getTotalBalance(isCache: Boolean = false): ResultData<Int>
    suspend fun getBasicUserInfo(isCache: Boolean = false): ResultData<BasicInfo>
    suspend fun getFullUserInfo(): ResultData<FullInfoResponse>
    suspend fun getLastTransfers(): ResultData<List<LastTransfersResponse>>
    suspend fun updateUserInfo(updateUserInfoRequest: UpdateInfoRequest): ResultData<Unit>
    suspend fun getPhone(): ResultData<String>
    suspend fun getUserVerified(): ResultData<Boolean>
    suspend fun setUserVerified(): ResultData<Unit>
}