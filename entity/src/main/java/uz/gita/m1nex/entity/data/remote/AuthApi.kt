package uz.gita.m1nex.entity.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.entity.data.model.request.SignInVerifyRequest
import uz.gita.m1nex.entity.data.model.request.SignUpVerifyRequest
import uz.gita.m1nex.entity.data.model.request.TokenRequest
import uz.gita.m1nex.entity.data.model.request.UpdateTokenRequest
import uz.gita.m1nex.entity.data.model.respone.*

internal interface AuthApi {

    @POST("v1/auth/sign-up")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): Response<SignUpResponse>

    @POST("v1/auth/sign-up/verify")
    suspend fun signUpVerify(
        @Body signUpVerifyRequest: SignUpVerifyRequest
    ): Response<SignUpVerifyResponse>

    @POST("v1/auth/sign-in")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): Response<SignInResponse>

    @POST("v1/auth/sign-in/verify")
    suspend fun signInVerify(
        @Body signInVerifyRequest: SignInVerifyRequest
    ): Response<SignInVerifyResponse>

    @POST("v1/auth/update-token")
    suspend fun updateToken(
        @Body updateTokenRequest: UpdateTokenRequest
    ): Response<UpdateTokenResponse>

    @POST("v1/auth/sign-up/resend")
    suspend fun signUpResend(
        @Body tokenRequest: TokenRequest
    ): Response<TokenResponse>

    @POST("v1/auth/sign-in/resend")
    suspend fun signInResend(
        @Body tokenRequest: TokenRequest
    ): Response<TokenResponse>
}
