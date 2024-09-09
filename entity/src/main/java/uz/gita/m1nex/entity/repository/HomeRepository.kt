package uz.gita.m1nex.entity.repository

import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.entity.data.model.request.UpdateInfoRequest
import uz.gita.m1nex.entity.data.model.respone.BasicInfoResponse
import uz.gita.m1nex.entity.data.model.respone.FullInfoResponse
import uz.gita.m1nex.entity.data.model.respone.LastTransfersResponse

interface HomeRepository {
    suspend fun getTotalBalance(): ResultData<Int>
    suspend fun getBasicUserInfo(): ResultData<BasicInfoResponse>
    suspend fun getFullUserInfo(): ResultData<FullInfoResponse>
    suspend fun getLastTransfers(): ResultData<List<LastTransfersResponse>>
    suspend fun updateUserInfo(updateUserInfoRequest: UpdateInfoRequest): ResultData<Unit>
}