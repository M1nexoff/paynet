package uz.gita.m1nex.entity.repository

import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.sign.SignIn
import uz.gita.m1nex.core.data.model.sign.SignUp
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.entity.data.model.request.SignUpRequest

interface AuthRepository {
    suspend fun signUp(signUpRequest: SignUp): ResultData<Unit>
    suspend fun signIn(signUpRequest: SignIn): ResultData<Unit>
    suspend fun signUpVerify(code:String): ResultData<Unit>
    suspend fun signInVerify(code: String): ResultData<Unit>
    suspend fun signUpResend(): ResultData<Unit>
    suspend fun signInResend(): ResultData<Unit>
    suspend fun updateToken(): ResultData<Unit>
    suspend fun logout(): ResultData<Unit>
}