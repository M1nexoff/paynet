package uz.gita.m1nex.entity.repository.impl

import kotlinx.coroutines.Dispatchers
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.fail
import uz.gita.m1nex.core.onFail
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.core.success
import uz.gita.m1nex.core.withContextSafety
import uz.gita.m1nex.entity.data.local.LocalStorage
import uz.gita.m1nex.entity.data.model.request.UpdateInfoRequest
import uz.gita.m1nex.entity.data.model.respone.BasicInfoResponse
import uz.gita.m1nex.entity.data.model.respone.FullInfoResponse
import uz.gita.m1nex.entity.data.model.respone.LastTransfersResponse
import uz.gita.m1nex.entity.data.remote.HomeApi
import uz.gita.m1nex.entity.data.util.mapTo
import uz.gita.m1nex.entity.data.util.toResultData
import uz.gita.m1nex.entity.repository.HomeRepository
import javax.inject.Inject

internal class HomeRepositoryImpl @Inject constructor(
    private val localStorage: LocalStorage,
    private val homeApi: HomeApi
) : HomeRepository {

    override suspend fun getTotalBalance(): ResultData<Int> = withContextSafety(Dispatchers.IO) {
        homeApi.getTotalBalance()
            .toResultData()
            .mapTo { it.totalBalance }
    }

    override suspend fun getBasicUserInfo(): ResultData<BasicInfoResponse> = withContextSafety(Dispatchers.IO) {
        homeApi.getBasicInfo()
            .toResultData()
    }

    override suspend fun getFullUserInfo(): ResultData<FullInfoResponse> = withContextSafety(Dispatchers.IO) {
        homeApi.getFullInfo()
            .toResultData()
    }

    override suspend fun getLastTransfers(): ResultData<List<LastTransfersResponse>> = withContextSafety(Dispatchers.IO) {
        homeApi.getLastTransfers()
            .toResultData()
    }

    override suspend fun updateUserInfo(updateUserInfoRequest: UpdateInfoRequest): ResultData<Unit> = withContextSafety(Dispatchers.IO) {
        homeApi.updateUserInfo(updateUserInfoRequest)
            .toResultData()
            .mapTo {  }
    }
}
