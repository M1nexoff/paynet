package uz.gita.m1nex.entity.data.di

import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.m1nex.entity.repository.AppRepository
import uz.gita.m1nex.entity.repository.AuthRepository
import uz.gita.m1nex.entity.repository.CacheRepository
import uz.gita.m1nex.entity.repository.CardRepository
import uz.gita.m1nex.entity.repository.HistoryRepository
import uz.gita.m1nex.entity.repository.HomeRepository
import uz.gita.m1nex.entity.repository.TransferRepository
import uz.gita.m1nex.entity.repository.impl.AppRepositoryImpl
import uz.gita.m1nex.entity.repository.impl.AuthRepositoryImpl
import uz.gita.m1nex.entity.repository.impl.CacheRepositoryImpl
import uz.gita.m1nex.entity.repository.impl.CardRepositoryImpl
import uz.gita.m1nex.entity.repository.impl.HistoryRepositoryImpl
import uz.gita.m1nex.entity.repository.impl.HomeRepositoryImpl
import uz.gita.m1nex.entity.repository.impl.TransferRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {


    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindAppRepository(impl: AppRepositoryImpl): AppRepository

    @Binds
    fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    fun bindCardRepository(impl: CardRepositoryImpl): CardRepository

    @Binds
    fun bindCacheRepository(impl: CacheRepositoryImpl): CacheRepository

    @Binds
    fun bindTransferRepository(impl: TransferRepositoryImpl): TransferRepository

    @Binds
    fun bindHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository

}