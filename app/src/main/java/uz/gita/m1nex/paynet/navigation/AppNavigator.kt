package uz.gita.m1nex.paynet.navigation

interface AppNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun navigateTo(screens: List<AppScreen>)
    suspend fun replaceAll(screen: AppScreen)
    suspend fun replace(screen: AppScreen)
    suspend fun back()
    suspend fun <T : AppScreen> backUntil(clazz: Class<T>)
}