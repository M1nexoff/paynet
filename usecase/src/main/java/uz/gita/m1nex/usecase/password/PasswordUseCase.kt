package uz.gita.m1nex.usecase.password

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData

interface PasswordUseCase {
    fun create(code:String): Flow<ResultData<Unit>>
    fun check(code:String): Flow<ResultData<Unit>>

}