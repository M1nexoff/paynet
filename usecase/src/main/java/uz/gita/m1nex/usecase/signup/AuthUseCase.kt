package uz.gita.m1nex.usecase.signup

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.sign.SignIn
import uz.gita.m1nex.core.data.model.sign.SignUp
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.entity.data.model.request.SignUpRequest

interface AuthUseCase {
    fun signUp(signUpRequest: SignUp): Flow<ResultData<Unit>>
    fun signIn(signUpRequest: SignIn): Flow<ResultData<Unit>>
    fun signUpVerify(code:String): Flow<ResultData<Unit>>
    fun signInVerify(code:String): Flow<ResultData<Unit>>
    fun signUpResend(): Flow<ResultData<Unit>>
    fun signInResend(): Flow<ResultData<Unit>>
    fun updateToken(): Flow<ResultData<Unit>>
    fun logout(): Flow<ResultData<Unit>>
}



