package uz.gita.m1nex.entity.data.local

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Invocation
import java.util.concurrent.TimeUnit


/**
 * Created by Sherzodbek Muhammadiev on 03/11/24.
 */


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ForceLocalCache

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ForceNetwork

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalCacheControl

enum class LocalCacheControlValue {
    Cache, Network
}


fun OkHttpClient.Builder.addLocalCacheInterceptor(
    context: Context,
    cache: Cache,
    maxAge: Int,
    maxStale: Int,
    timeUnit: TimeUnit
): OkHttpClient.Builder {
    cache(cache)
    addInterceptor {
        val request = it.request()
        val requestBuilder = request.newBuilder()
        if (!isInternetAvailable(context)) {
            request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=${60*60*24}")
                .removeHeader("Pragma")
                .build()
        } else {
            val invocation: Invocation? = request.tag(Invocation::class.java)
            if (invocation != null) {
                Log.d("Cache22", "invocation: $invocation")
                val method = invocation.method()
                val values = invocation.arguments()
                method.parameterAnnotations.forEachIndexed { index, annotations ->
                    annotations.forEach { annotation ->
                        when (annotation) {
                            is ForceNetwork -> requestBuilder.cacheControl(CacheControl.FORCE_NETWORK)
                            is ForceLocalCache -> request.newBuilder()
                                .header(
                                    "Cache-Control",
                                    "public, only-if-cached, max-stale=${60*60*24}"
                                )
                                .removeHeader("Pragma")
                                .build()

                            is LocalCacheControl -> {
                                val value = values[index] as? LocalCacheControlValue
                                    ?: throw IllegalArgumentException("For LocalCacheControl annotation required params must be of type LocalCacheControlValue")
                                when (value) {
                                    LocalCacheControlValue.Cache -> requestBuilder.cacheControl(
                                        CacheControl.Builder().onlyIfCached()
                                            .maxStale(maxStale, timeUnit).build()
                                    )

                                    LocalCacheControlValue.Network -> requestBuilder.cacheControl(
                                        CacheControl.FORCE_NETWORK
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        val response = it.proceed(requestBuilder.build())
        if (response.cacheResponse != null) {
            Log.d("Cache22", "Response served from cache")
        }
        if (response.networkResponse != null) {
            Log.d("Cache22", "Response served from network")
        }
        Log.d("Cache22", "response: $response")
        response

    }
    addNetworkInterceptor {
        val request = it.request()
        it.proceed(request).newBuilder()
            .apply {
                if (request.header("Cache-Control") != null) {
                        removeHeader("Cache-Control")
                        .removeHeader("pragma")
                        .header("Cache-Control", "public, max-age=${maxAge}")
                }
            }
            .build();
    }
    return this

}

fun isInternetAvailable(context: Context): Boolean {
    var isConnected: Boolean = false // Initial Value
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}