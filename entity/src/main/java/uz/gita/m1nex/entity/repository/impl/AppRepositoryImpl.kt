package uz.gita.m1nex.entity.repository.impl

import kotlinx.coroutines.Dispatchers
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.LaunchData
import uz.gita.m1nex.core.fail
import uz.gita.m1nex.core.success
import uz.gita.m1nex.core.withContextSafety
import uz.gita.m1nex.entity.data.local.LocalStorage
import uz.gita.m1nex.entity.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val localStorage: LocalStorage
): AppRepository {
    override suspend fun getLaunchMode(): LaunchData = withContextSafety(Dispatchers.IO) {
            if (localStorage.isFirstRun) {
                localStorage.isFirstRun = false
                LaunchData.FIRST_TIME
            }
            else if (localStorage.isSignIn) LaunchData.SIGNED
            else LaunchData.UNSIGNED

    }

    override suspend fun createPassword(code: String) = withContextSafety(Dispatchers.IO) {
        localStorage.password = code
        ResultData.success(Unit)
    }

    override suspend fun checkPassword(code: String): ResultData<Unit> = withContextSafety(Dispatchers.IO) {
        if (localStorage.password == code){
            ResultData.success(Unit)
        }else{
            ResultData.fail("Not matches")
        }
    }
}
