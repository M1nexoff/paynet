package uz.gita.m1nex.entity.data.remote
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Request
import android.content.SharedPreferences
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Route
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.entity.data.local.LocalStorage
import uz.gita.m1nex.entity.data.model.request.UpdateTokenRequest
import uz.gita.m1nex.entity.data.util.toResultData

internal class AuthenticationInterceptor(
    private val localStorage: LocalStorage,
    private val authApi: AuthApi
) : Authenticator {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = runBlocking { localStorage.accessToken }
        synchronized(this) {
            val updatedToken = runBlocking { localStorage.accessToken }
            val token = if (currentToken != updatedToken) updatedToken else {
                val newSessionResponse = runBlocking { authApi.updateToken(UpdateTokenRequest(localStorage.refreshToken)) }
                if (newSessionResponse.isSuccessful && newSessionResponse.body() != null) {
                    newSessionResponse.body()?.let { body ->
                        runBlocking {
                            localStorage.accessToken = body.accessToken
                            localStorage.refreshToken = body.refreshToken
                        }
                        body.accessToken
                    }
                } else null
            }
            if (token == null) {
                runBlocking {
//                    appRepository.clearUserData()
//                    direction.logout()
                }
            }
            return if (token != null) response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
                .build() else null
        }
    }
}