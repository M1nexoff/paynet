package uz.gita.m1nex.entity.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.m1nex.entity.data.local.LocalStorage
import uz.gita.m1nex.entity.data.local.addLocalCacheInterceptor
import uz.gita.m1nex.entity.data.local.isInternetAvailable
import uz.gita.m1nex.entity.data.remote.AuthApi
import uz.gita.m1nex.entity.data.remote.CardApi
import uz.gita.m1nex.entity.data.remote.HomeApi
import uz.gita.m1nex.entity.data.remote.TransferApi
import uz.gita.m1nex.entity.data.util.AuthenticationInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Named("Auth")
    @Singleton
    fun provideAuthOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(ChuckerInterceptor(context))
            .build()

    @Provides
    @Named("Home")
    @Singleton
    fun provideHomeOkHttpClient(@ApplicationContext context: Context,localStorage: LocalStorage,api: AuthApi): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(ChuckerInterceptor(context))
//            .addLocalCacheInterceptor(
//                context,
//                Cache(context.cacheDir, 10 * 1024 * 1024),
//                9999,
//                9999,
//                TimeUnit.HOURS
//            )
            .cache(Cache(context.cacheDir, 10 * 1024 * 1024))
            .addInterceptor { chain ->
                var request: Request = chain.request()
                if (!isInternetAvailable(context)) {
                    val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
                    request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .removeHeader("Pragma")
                        .build()
                }
                chain.proceed(request)
            }
            .addNetworkInterceptor {
                val response = it.proceed(it.request())
                val maxAge = 60

                response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .removeHeader("Pragma")
                    .build();

            }
            .authenticator(AuthenticationInterceptor(localStorage,api))
            .build()


    @Provides
    @Named("Auth")
    @Singleton
    fun provideAuthRetrofit(@Named("Auth") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://195.158.16.140/mobile-bank/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named("Home")
    @Singleton
    fun provideHomeRetrofit(@Named("Home") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://195.158.16.140/mobile-bank/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(@Named("Auth") retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeApi(@Named("Home") retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }
    @Provides
    @Singleton
    fun provideTransferApi(@Named("Home") retrofit: Retrofit): TransferApi {
        return retrofit.create(TransferApi::class.java)
    }
    @Provides
    @Singleton
    fun provideCardApi(@Named("Home") retrofit: Retrofit): CardApi {
        return retrofit.create(CardApi::class.java)
    }

    @Provides
    fun provideGson(): Gson = Gson()

}
