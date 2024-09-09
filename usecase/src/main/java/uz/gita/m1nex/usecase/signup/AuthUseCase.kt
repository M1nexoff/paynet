package uz.gita.m1nex.usecase.signup

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.entity.data.model.request.*
import uz.gita.m1nex.entity.data.model.respone.*

interface AuthUseCase {
    fun signUp(signUpRequest: SignUpRequest): Flow<ResultData<Unit>>
    fun signIn(signUpRequest: SignInRequest): Flow<ResultData<Unit>>
    fun signUpVerify(code:String): Flow<ResultData<Unit>>
    fun signInVerify(code:String): Flow<ResultData<Unit>>
    fun signUpResend(): Flow<ResultData<Unit>>
    fun signInResend(): Flow<ResultData<Unit>>
    fun updateToken(): Flow<ResultData<Unit>>
}