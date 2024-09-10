package uz.gita.m1nex.entity.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.m1nex.entity.data.local.LocalStorage
import uz.gita.m1nex.entity.data.remote.AuthApi
import uz.gita.m1nex.entity.data.remote.AuthenticationInterceptor
import uz.gita.m1nex.entity.data.remote.HomeApi
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("Auth")
    @Singleton
    fun provideAuthOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(ChuckerInterceptor(context))
            .build()

    @Provides
    @Named("Home")
    @Singleton
    fun provideHomeOkHttpClient(@ApplicationContext context: Context,localStorage: LocalStorage,api: AuthApi): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(ChuckerInterceptor(context))
            .addInterceptor(AuthenticationInterceptor(localStorage,api))
            .build()


    @Provides
    @Named("Auth")
    @Singleton
    fun provideAuthRetrofit(@Named("Auth") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://195.158.16.140/mobile-bank/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named("Home")
    @Singleton
    fun provideHomeRetrofit(@Named("Home") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://195.158.16.140/mobile-bank/v1/")
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
}
