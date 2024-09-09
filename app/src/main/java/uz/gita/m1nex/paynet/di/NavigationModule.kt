package uz.gita.m1nex.paynet.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.m1nex.paynet.navigation.AppNavigator
import uz.gita.m1nex.paynet.navigation.AppNavigatorDispatcher
import uz.gita.m1nex.paynet.navigation.AppNavigatorHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun appNavigator(impl: AppNavigatorDispatcher): AppNavigator

    @Binds
    fun appNavigatorHandler(impl: AppNavigatorDispatcher): AppNavigatorHandler
}