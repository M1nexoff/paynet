package uz.gita.m1nex.entity.repository.impl

import kotlinx.coroutines.Dispatchers
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.sign.SignIn
import uz.gita.m1nex.core.data.model.sign.SignUp
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.core.withContextSafety
import uz.gita.m1nex.entity.data.local.LocalStorage
import uz.gita.m1nex.entity.data.model.request.SignInVerifyRequest
import uz.gita.m1nex.entity.data.model.request.SignUpVerifyRequest
import uz.gita.m1nex.entity.data.model.request.TokenRequest
import uz.gita.m1nex.entity.data.model.request.UpdateTokenRequest
import uz.gita.m1nex.entity.data.remote.AuthApi
import uz.gita.m1nex.entity.data.util.mapTo
import uz.gita.m1nex.entity.data.util.toRequest
import uz.gita.m1nex.entity.data.util.toResultData
import uz.gita.m1nex.entity.repository.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val localStorage: LocalStorage,
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun signUp(signUpRequest: SignUp): ResultData<Unit> =
        withContextSafety(Dispatchers.IO) {
            authApi.signUp(signUpRequest.toRequest())
                .toResultData()
                .mapTo {
                    localStorage.token = it.token
                    localStorage.isSignIn = false
                    localStorage.isFirstRun = false
                }
        }

    override suspend fun signIn(signUpRequest: SignIn): ResultData<Unit> =
        withContextSafety(Dispatchers.IO) {
            authApi.signIn(signUpRequest.toRequest())
                .toResultData()
                .mapTo {
                    localStorage.token = it.token
                    localStorage.isSignIn = false
                    localStorage.isFirstRun = false
                }
        }

    override suspend fun signUpVerify(code: String): ResultData<Unit> =
        withContextSafety(Dispatchers.IO) {
            authApi.signUpVerify(SignUpVerifyRequest(localStorage.token, code))
                .toResultData()
                .mapTo {
                    localStorage.token = ""
                    localStorage.accessToken = it.accessToken
                    localStorage.refreshToken = it.refreshToken
                    localStorage.isSignIn = true
                }
        }

    override suspend fun signInVerify(code: String): ResultData<Unit> =
        withContextSafety(Dispatchers.IO) {
            authApi.signInVerify(SignInVerifyRequest(localStorage.token, code))
                .toResultData()
                .mapTo {
                    localStorage.token = ""
                    localStorage.accessToken = it.accessToken
                    localStorage.refreshToken = it.refreshToken
                    localStorage.isSignIn = true
                }
        }

    override suspend fun signUpResend(): ResultData<Unit> = withContextSafety(Dispatchers.IO) {
        authApi.signUpResend(TokenRequest(localStorage.token))
            .toResultData()
            .mapTo {
                localStorage.token = it.token
            }
    }

    override suspend fun signInResend(): ResultData<Unit> = withContextSafety(Dispatchers.IO) {
        authApi.signInResend(TokenRequest(localStorage.token))
            .toResultData()
            .mapTo {
                localStorage.token = it.token
            }
    }

    override suspend fun updateToken(): ResultData<Unit> = withContextSafety(Dispatchers.IO) {
        authApi.updateToken(UpdateTokenRequest(localStorage.refreshToken))
            .toResultData()
            .mapTo {
                localStorage.accessToken = it.accessToken
                localStorage.refreshToken = it.refreshToken
                localStorage.token = ""
            }
    }

    override suspend fun logout(): ResultData<Unit> = withContextSafety(Dispatchers.IO) {
        localStorage.token = ""
        localStorage.accessToken = ""
        localStorage.refreshToken = ""
        localStorage.isSignIn = false
        ResultData.Success(Unit)
    }
}