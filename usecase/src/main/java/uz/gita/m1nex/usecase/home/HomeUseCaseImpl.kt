package uz.gita.m1nex.usecase.home

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.flowWithCatch
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.core.success
import uz.gita.m1nex.entity.data.model.request.UpdateInfoRequest
import uz.gita.m1nex.entity.data.model.respone.BasicInfoResponse
import uz.gita.m1nex.entity.data.model.respone.FullInfoResponse
import uz.gita.m1nex.entity.data.model.respone.LastTransfersResponse
import uz.gita.m1nex.entity.repository.HomeRepository
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
) : HomeUseCase {
    override fun getTotalBalance(): Flow<ResultData<Int>> = flowWithCatch {
        val result = repository.getTotalBalance()
        emit(result)
    }

    override fun getBasicUserInfo(): Flow<ResultData<BalanceAndName>> = flowWithCatch {
        val result = repository.getBasicUserInfo()
        val result2 = repository.getTotalBalance()
        result.onSuccess {
            val name = firstName
            result2.onSuccess {

                emit(ResultData.success(BalanceAndName(this, name)))
            }
        }
//        emit()
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
}
data class BalanceAndName(val balance: Int, val name: String)