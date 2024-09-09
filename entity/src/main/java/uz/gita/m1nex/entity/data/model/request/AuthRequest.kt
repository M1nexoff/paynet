package uz.gita.m1nex.entity.data.model.request

data class SignUpRequest(
    val phone: String,
    val password: String,
    val `first-name`: String,
    val `last-name`: String,
    val `born-date`: String,
    val gender: String
)
data class SignUpVerifyRequest(val token: String, val code: String)
data class SignInRequest(val phone: String, val password: String)
data class SignInVerifyRequest(val token: String, val code: String)
data class UpdateTokenRequest(val `refresh-token`: String)
data class TokenRequest(val token: String)