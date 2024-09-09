package uz.gita.m1nex.usecase.splash

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.LaunchData
import uz.gita.m1nex.core.flowWithCatch
import uz.gita.m1nex.core.success
import uz.gita.m1nex.entity.repository.AppRepository
import uz.gita.m1nex.usecase.splash.SplashUseCase
import javax.inject.Inject

class SplashUseCaseImpl @Inject constructor(
    private val appRepository: AppRepository
) : SplashUseCase {

    override fun checkLaunchMode(): Flow<ResultData<LaunchData>> = flowWithCatch {
        emit(ResultData.success(appRepository.getLaunchMode()))
    }
}