package uz.gita.m1nex.entity.repository

import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.entity.data.model.respone.BasicInfoResponse
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.core.data.model.transfer.LastTransferData

interface CacheRepository {
    suspend fun getTotalBalance() : ResultData<Int>
    suspend fun getBasicUserInfo() : ResultData<BasicInfoResponse>
    suspend fun getCards() : ResultData<List<CardData>>
    suspend fun getLastTransfers() : ResultData<List<LastTransferData>>
    suspend fun deleteAllLastTransfer() : ResultData<Unit>
}