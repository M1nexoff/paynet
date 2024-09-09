package uz.gita.m1nex.entity.data.model.respone

import com.google.gson.annotations.SerializedName

data class SignUpResponse(val token: String)
data class SignUpVerifyResponse(@SerializedName("refresh-token") val refreshToken: String,@SerializedName("access-token") val accessToken: String)
data class SignInResponse(val token: String)
data class SignInVerifyResponse(@SerializedName("refresh-token") val refreshToken: String, @SerializedName("access-token") val accessToken: String)
data class UpdateTokenResponse(@SerializedName("refresh-token") val refreshToken: String, @SerializedName("access-token") val accessToken: String)
data class TokenResponse(val token: String)