package uz.gita.m1nex.usecase.splash

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.LaunchData

interface SplashUseCase {
    fun checkLaunchMode(): Flow<ResultData<LaunchData>>
}