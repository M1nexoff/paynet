package uz.gita.m1nex.entity.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.LaunchData

interface AppRepository {
    suspend fun getLaunchMode() : LaunchData
    suspend fun createPassword(code:String): ResultData<Unit>
    suspend fun checkPassword(code:String): ResultData<Unit>
}