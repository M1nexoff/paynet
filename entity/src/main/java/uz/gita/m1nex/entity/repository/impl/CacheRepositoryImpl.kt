package uz.gita.m1nex.entity.repository.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.entity.data.model.respone.BasicInfoResponse
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.core.data.model.transfer.LastTransferData
import uz.gita.m1nex.core.success
import uz.gita.m1nex.core.withContextSafety
import uz.gita.m1nex.entity.data.local.LocalStorage
import uz.gita.m1nex.entity.data.room.AppDatabase
import uz.gita.m1nex.entity.repository.CacheRepository
import javax.inject.Inject

internal class CacheRepositoryImpl @Inject constructor(
    private val localStorage: LocalStorage,
    private val gson: Gson,
    private val appDatabase: AppDatabase
) : CacheRepository {
    private val lastTransferDao = appDatabase.lastTransferDao()
    override suspend fun getTotalBalance(): ResultData<Int> = withContextSafety(Dispatchers.IO){
        ResultData.Success(localStorage.totalBalance)
    }

    override suspend fun getBasicUserInfo(): ResultData<BasicInfoResponse> = withContextSafety(Dispatchers.IO){
        ResultData.Success(BasicInfoResponse(localStorage.firstName, localStorage.genderType, localStorage.age))
    }

    override suspend fun getCards(): ResultData<List<CardData>> = withContextSafety(Dispatchers.IO) {
        ResultData.success(
            if (localStorage.cards.isNotEmpty())
                // Deserialize the stored JSON into a list of CardData
                gson.fromJson(localStorage.cards, object : TypeToken<List<CardData>>() {}.type)
            else
                emptyList<CardData>()
        )
    }

    override suspend fun getLastTransfers(): ResultData<List<LastTransferData>> = withContext(Dispatchers.IO) {
        ResultData.Success(
            lastTransferDao.getAllResults().map { LastTransferData(it.id, it.owner, it.pan) }
        )
    }

    override suspend fun deleteAllLastTransfer(): ResultData<Unit> = withContext(Dispatchers.IO) {
        lastTransferDao.deleteAll()
        ResultData.success(Unit)
    }

}