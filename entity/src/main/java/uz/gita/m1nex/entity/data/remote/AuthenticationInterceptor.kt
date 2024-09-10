package uz.gita.m1nex.entity.data.remote
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Request
import android.content.SharedPreferences
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.entity.data.local.LocalStorage
import uz.gita.m1nex.entity.data.model.request.UpdateTokenRequest
import uz.gita.m1nex.entity.data.util.toResultData

class AuthenticationInterceptor(
    private val localStorage: LocalStorage,
    private val authApi: AuthApi
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val accessToken = localStorage.accessToken
        val refreshToken = localStorage.refreshToken

        val requestBuilder = originalRequest.newBuilder()
        accessToken.let { requestBuilder.addHeader("Authorization", "Bearer $it") }

        val newRequest = requestBuilder.build()
        val response = chain.proceed(newRequest)

        if (response.code == 401) {
            try {
                runBlocking {
                    val updateTokenResponse = async { authApi.updateToken(UpdateTokenRequest(refreshToken)).toResultData()}
                    updateTokenResponse.await().onSuccess {
                        localStorage.accessToken = accessToken
                        localStorage.refreshToken = refreshToken
                    }
                }
                val newRequestBuilder = originalRequest.newBuilder()
                    .addHeader("Authorization", "Bearer")
                    .build()
                return chain.proceed(newRequestBuilder)
            } catch (e: Exception) {
                throw e
            }

        }

        return response
    }
}