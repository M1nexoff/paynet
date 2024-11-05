package uz.gita.m1nex.usecase.home

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.BasicInfo
import uz.gita.m1nex.core.flowWithCatch
import uz.gita.m1nex.core.data.model.UpdateInfoRequest
import uz.gita.m1nex.core.data.model.FullInfoResponse
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.core.success
import uz.gita.m1nex.entity.data.model.respone.LastTransfersResponse
import uz.gita.m1nex.entity.repository.CacheRepository
import uz.gita.m1nex.entity.repository.HomeRepository
import javax.inject.Inject

internal class HomeUseCaseImpl @Inject constructor(
    private val repository: HomeRepository,
    private val cache: CacheRepository
) : HomeUseCase {
    override fun getTotalBalance(): Flow<ResultData<Int>> = flowWithCatch {
        repository.getTotalBalance(true).onSuccess {
            emit(ResultData.success(this))
        }
        delay(3000)
        val result = repository.getTotalBalance(false)
        emit(result)
    }

    override fun getPhone(): Flow<ResultData<String>> = flowWithCatch {
        val result = repository.getPhone()
        emit(result)
    }

    override fun getBasicUserInfo(): Flow<ResultData<BasicInfo>> = flowWithCatch {
//        val coroutineScope = CoroutineScope(Dispatchers.IO)
//        val result = coroutineScope.async { repository.getBasicUserInfo() }
//        val result2 = coroutineScope.async { repository.getTotalBalance() }
//        val name = result.await().onSuccess {  }
//        val balance = result2.await()
//        emit(ResultData.success(BalanceAndName(name, result.await())))

        val result = repository.getBasicUserInfo(true).onSuccess {
            emit(ResultData.Success(this))
        }
        delay(3000)
        emit(repository.getBasicUserInfo())
    }

    override fun getFullUserInfo(): Flow<ResultData<FullInfoResponse>> = flowWithCatch {
        val result = repository.getFullUserInfo()
        emit(result)
    }

    override fun getLastTransfers(): Flow<ResultData<List<LastTransfersResponse>>> = flowWithCatch {
        val result = repository.getLastTransfers()
        emit(result)
    }

    override fun updateUserInfo(updateUserInfoRequest: UpdateInfoRequest): Flow<ResultData<Unit>> = flowWithCatch {
        val result = repository.updateUserInfo(updateUserInfoRequest)
        emit(result)
    }

    override fun isVerified(): Flow<ResultData<Boolean>> = flowWithCatch {
        val result = repository.getUserVerified()
        emit(result)
    }
    override fun setVerified(): Flow<ResultData<Unit>> = flowWithCatch {
        val result = repository.setUserVerified()
        emit(result)
    }
}
