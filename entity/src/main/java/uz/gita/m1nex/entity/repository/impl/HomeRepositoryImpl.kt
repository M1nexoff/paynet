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

class HomeRepositoryImpl @Inject constructor(
    private val localStorage: LocalStorage,
    private val homeApi: HomeApi
) : HomeRepository {

    override suspend fun getTotalBalance(): ResultData<Int> = withContextSafety(Dispatchers.IO) {
        homeApi.getTotalBalance()
            .toResultData()
            .onSuccess { ResultData.success(totalBalance) }
            .onFail { ResultData.fail(message) }
            .mapTo { it.totalBalance }
    }

    override suspend fun getBasicUserInfo(): ResultData<BasicInfoResponse> = withContextSafety(Dispatchers.IO) {
        homeApi.getBasicInfo()
            .toResultData()
            .onSuccess { ResultData.success(this) }
            .onFail { ResultData.fail(message) }
    }

    override suspend fun getFullUserInfo(): ResultData<FullInfoResponse> = withContextSafety(Dispatchers.IO) {
        homeApi.getFullInfo()
            .toResultData()
            .onSuccess { ResultData.success(this) }
            .onFail { ResultData.fail(message) }
    }

    override suspend fun getLastTransfers(): ResultData<List<LastTransfersResponse>> = withContextSafety(Dispatchers.IO) {
        homeApi.getLastTransfers()
            .toResultData()
            .onSuccess { ResultData.success(this) }
            .onFail { ResultData.fail(message) }
    }

    override suspend fun updateUserInfo(updateUserInfoRequest: UpdateInfoRequest): ResultData<Unit> = withContextSafety(Dispatchers.IO) {
        homeApi.updateUserInfo(updateUserInfoRequest)
            .toResultData()
            .onSuccess { ResultData.success(Unit) }
            .onFail { ResultData.fail(message) }
            .mapTo {

            }
    }
}
