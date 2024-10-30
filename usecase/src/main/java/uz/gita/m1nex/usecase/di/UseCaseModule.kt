package uz.gita.m1nex.usecase.di

import androidx.annotation.Keep
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uz.gita.m1nex.usecase.card.CardUseCase
import uz.gita.m1nex.usecase.card.CardUseCaseImpl
import uz.gita.m1nex.usecase.history.HistoryUseCase
import uz.gita.m1nex.usecase.history.HistoryUseCaseImpl
import uz.gita.m1nex.usecase.home.HomeUseCase
import uz.gita.m1nex.usecase.home.HomeUseCaseImpl
import uz.gita.m1nex.usecase.password.PasswordUseCase
import uz.gita.m1nex.usecase.password.PasswordUseCaseImpl
import uz.gita.m1nex.usecase.signup.AuthUseCase
import uz.gita.m1nex.usecase.signup.AuthUseCaseImpl
import uz.gita.m1nex.usecase.splash.SplashUseCase
import uz.gita.m1nex.usecase.splash.SplashUseCaseImpl
import uz.gita.m1nex.usecase.transfer.TransferUseCase
import uz.gita.m1nex.usecase.transfer.TransferUseCaseImpl

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

    @Binds
    fun bindCardUsaCase(impl: CardUseCaseImpl): CardUseCase

    @Binds
    fun bindTransferUseCase(impl: TransferUseCaseImpl): TransferUseCase

    @Binds
    fun bindHistoryUseCase(impl: HistoryUseCaseImpl): HistoryUseCase

}