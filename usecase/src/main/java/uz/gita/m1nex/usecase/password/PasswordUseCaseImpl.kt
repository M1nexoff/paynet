package uz.gita.m1nex.usecase.password

import android.util.Log
import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.flowWithCatch
import uz.gita.m1nex.entity.repository.AppRepository
import javax.inject.Inject

internal class PasswordUseCaseImpl @Inject constructor(
    private val appRepository: AppRepository
) : PasswordUseCase {
    init {
        Log.d("TTT", "PasswordUseCaseImpl: init")
    }
    override fun create(code: String): Flow<ResultData<Unit>> = flowWithCatch {
        val result = appRepository.createPassword(code)
        emit(result)
    }

    override fun check(code: String): Flow<ResultData<Unit>> = flowWithCatch {
        val result = appRepository.checkPassword(code)
        emit(result)
    }
}