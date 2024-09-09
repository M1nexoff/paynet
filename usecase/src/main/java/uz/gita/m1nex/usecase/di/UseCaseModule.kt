package uz.gita.m1nex.usecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uz.gita.m1nex.usecase.home.HomeUseCase
import uz.gita.m1nex.usecase.home.HomeUseCaseImpl
import uz.gita.m1nex.usecase.password.PasswordUseCase
import uz.gita.m1nex.usecase.password.PasswordUseCaseImpl
import uz.gita.m1nex.usecase.signup.AuthUseCase
import uz.gita.m1nex.usecase.signup.AuthUseCaseImpl
import uz.gita.m1nex.usecase.splash.SplashUseCase
import uz.gita.m1nex.usecase.splash.SplashUseCaseImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface UseCaseModule {
    @Binds
    fun bindSplashUseCase(impl: SplashUseCaseImpl): SplashUseCase

    @Binds
    fun bindLoginUseCase(impl: AuthUseCaseImpl): AuthUseCase

    @Binds
    fun bindPasswordUsaCase(impl: PasswordUseCaseImpl): PasswordUseCase

    @Binds
    fun bindHomeUsaCase(impl: HomeUseCaseImpl): HomeUseCase

}