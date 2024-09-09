package uz.gita.m1nex.entity.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.m1nex.entity.repository.AppRepository
import uz.gita.m1nex.entity.repository.AuthRepository
import uz.gita.m1nex.entity.repository.HomeRepository
import uz.gita.m1nex.entity.repository.impl.AppRepositoryImpl
import uz.gita.m1nex.entity.repository.impl.AuthRepositoryImpl
import uz.gita.m1nex.entity.repository.impl.HomeRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindAppRepository(impl: AppRepositoryImpl): AppRepository

    @Binds
    fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

}