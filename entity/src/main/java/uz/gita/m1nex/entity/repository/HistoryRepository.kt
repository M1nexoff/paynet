package uz.gita.m1nex.entity.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.Child
import uz.gita.m1nex.core.data.model.transfer.TransferData

interface HistoryRepository {
    suspend fun getHistory() : Flow<PagingData<Child>>
}