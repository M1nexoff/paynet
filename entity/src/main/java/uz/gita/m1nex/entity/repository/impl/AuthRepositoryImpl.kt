package uz.gita.m1nex.entity.repository.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.fail
import uz.gita.m1nex.core.onFail
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.core.success
import uz.gita.m1nex.core.withContextSafety
import uz.gita.m1nex.entity.data.local.LocalStorage
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.entity.data.model.request.SignInVerifyRequest
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.entity.data.model.request.SignUpVerifyRequest
import uz.gita.m1nex.entity.data.model.request.TokenRequest
import uz.gita.m1nex.entity.data.model.request.UpdateTokenRequest
import uz.gita.m1nex.entity.data.remote.AuthApi
import uz.gita.m1nex.entity.data.util.mapTo
import uz.gita.m1nex.entity.data.util.toResultData
import uz.gita.m1nex.entity.repository.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val localStorage: LocalStorage,
    private val authApi: AuthApi
): AuthRepository {
   override suspend fun signUp(signUpRequest: SignUpRequest): ResultData<Unit> = withContextSafety(Dispatchers.IO){
        authApi.signUp(signUpRequest)
            .toResultData()
            .mapTo {
            localStorage.token = it.token
            localStorage.isSignIn = false
            localStorage.isFirstRun = false
        }
    }

    override suspend fun signIn(signUpRequest: SignInRequest): ResultData<Unit> = withContextSafety(Dispatchers.IO){
        authApi.signIn(signUpRequest)
            .toResultData()
            .mapTo {
                localStorage.token = it.token
                localStorage.isSignIn = false
                localStorage.isFirstRun = false
            }
    }

    override suspend fun signUpVerify(code: String): ResultData<Unit> = withContextSafety(Dispatchers.IO){
        authApi.signUpVerify(SignUpVerifyRequest(localStorage.token,code))
            .toResultData()
            .mapTo{
                localStorage.token = ""
                localStorage.accessToken = it.accessToken
                localStorage.refreshToken = it.refreshToken
                localStorage.isSignIn = true
            }
    }

    override suspend fun signInVerify(code: String): ResultData<Unit> = withContextSafety(Dispatchers.IO){
        authApi.signInVerify(SignInVerifyRequest(localStorage.token,code))
            .toResultData()
            .mapTo{
                localStorage.token = ""
                localStorage.accessToken = it.accessToken
                localStorage.refreshToken = it.refreshToken
                localStorage.isSignIn = true
            }
    }

    override suspend fun signUpResend(): ResultData<Unit> = withContextSafety(Dispatchers.IO){
        authApi.signUpResend(TokenRequest(localStorage.token))
            .toResultData()
            .mapTo{
                localStorage.token = it.token
            }
    }

    override suspend fun signInResend(): ResultData<Unit> = withContextSafety(Dispatchers.IO){
        authApi.signInResend(TokenRequest(localStorage.token))
            .toResultData()
            .mapTo{
                localStorage.token = it.token
            }
    }

    override suspend fun updateToken(): ResultData<Unit> = withContextSafety(Dispatchers.IO){
        authApi.updateToken(UpdateTokenRequest(localStorage.refreshToken))
            .toResultData()
            .mapTo{
                localStorage.accessToken = it.accessToken
                localStorage.refreshToken = it.refreshToken
                localStorage.token = ""
            }
    }
}