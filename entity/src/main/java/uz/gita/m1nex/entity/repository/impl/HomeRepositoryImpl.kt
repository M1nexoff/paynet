package uz.gita.m1nex.entity.repository.impl

import kotlinx.coroutines.Dispatchers
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.BasicInfo
import uz.gita.m1nex.core.data.model.FullInfoResponse
import uz.gita.m1nex.core.data.model.UpdateInfoRequest
import uz.gita.m1nex.core.withContextSafety
import uz.gita.m1nex.entity.data.local.LocalStorage
import uz.gita.m1nex.entity.data.model.respone.LastTransfersResponse
import uz.gita.m1nex.entity.data.remote.HomeApi
import uz.gita.m1nex.entity.data.util.mapTo
import uz.gita.m1nex.entity.data.util.toBasicInfo
import uz.gita.m1nex.entity.data.util.toResultData
import uz.gita.m1nex.entity.repository.HomeRepository
import javax.inject.Inject

internal class HomeRepositoryImpl @Inject constructor(
    private val localStorage: LocalStorage,
    private val homeApi: HomeApi
) : HomeRepository {

    override suspend fun getTotalBalance(isCache: Boolean): ResultData<Int> =
        withContextSafety(Dispatchers.IO) {
            if (isCache) {
                homeApi.getTotalBalanceCache()
                    .toResultData()
                    .mapTo {
                        localStorage.totalBalance = it.totalBalance
                        it.totalBalance
                    }
            } else {
                homeApi.getTotalBalance()
                    .toResultData()
                    .mapTo {
                        localStorage.totalBalance = it.totalBalance
                        it.totalBalance
                    }

            }
        }

    override suspend fun getBasicUserInfo(isCache: Boolean): ResultData<BasicInfo> =
        withContextSafety(Dispatchers.IO) {
            if (isCache) {
                homeApi.getBasicInfoCache()
                    .toResultData()
                    .mapTo {
                        localStorage.firstName = it.firstName
                        localStorage.genderType = it.genderType
                        localStorage.age = it.age
                        it.toBasicInfo()
                    }
            } else {
                homeApi.getBasicInfo()
                    .toResultData()
                    .mapTo {
                        localStorage.firstName = it.firstName
                        localStorage.genderType = it.genderType
                        localStorage.age = it.age
                        it.toBasicInfo()
                    }
            }
        }

    override suspend fun getFullUserInfo(): ResultData<FullInfoResponse> =
        withContextSafety(Dispatchers.IO) {
            homeApi.getFullInfo()
                .toResultData()
        }

    override suspend fun getLastTransfers(): ResultData<List<LastTransfersResponse>> =
        withContextSafety(Dispatchers.IO) {
            homeApi.getLastTransfers()
                .toResultData()
        }

    override suspend fun updateUserInfo(updateUserInfoRequest: UpdateInfoRequest): ResultData<Unit> =
        withContextSafety(Dispatchers.IO) {
            homeApi.updateUserInfo(updateUserInfoRequest)
                .toResultData()
                .mapTo { }
        }

    override suspend fun getPhone(): ResultData<String> = withContextSafety(Dispatchers.IO) {
        ResultData.Success(localStorage.phone)
    }

    override suspend fun getUserVerified(): ResultData<Boolean> =
        withContextSafety(Dispatchers.IO) {
            ResultData.Success(localStorage.isVerified)
        }

    override suspend fun setUserVerified(): ResultData<Unit> = withContextSafety(Dispatchers.IO) {
        localStorage.isVerified = true
        ResultData.Success(Unit)
    }
}