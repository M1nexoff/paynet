package uz.gita.m1nex.entity.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.entity.data.model.request.SignInVerifyRequest
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.entity.data.model.request.SignUpVerifyRequest
import uz.gita.m1nex.entity.data.model.request.TokenRequest
import uz.gita.m1nex.entity.data.model.request.UpdateTokenRequest
import uz.gita.m1nex.entity.data.model.respone.SignInVerifyResponse
import uz.gita.m1nex.entity.data.model.respone.SignUpResponse
import uz.gita.m1nex.entity.data.model.respone.SignUpVerifyResponse
import uz.gita.m1nex.entity.data.model.respone.TokenResponse
import uz.gita.m1nex.entity.data.model.respone.UpdateTokenResponse

interface AuthRepository {
    suspend fun signUp(signUpRequest: SignUpRequest): ResultData<Unit>
    suspend fun signIn(signUpRequest: SignInRequest): ResultData<Unit>
    suspend fun signUpVerify(code:String): ResultData<Unit>
    suspend fun signInVerify(code: String): ResultData<Unit>
    suspend fun signUpResend(): ResultData<Unit>
    suspend fun signInResend(): ResultData<Unit>
    suspend fun updateToken(): ResultData<Unit>
}