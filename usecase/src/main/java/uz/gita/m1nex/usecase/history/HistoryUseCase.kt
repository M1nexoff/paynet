package uz.gita.m1nex.usecase.history

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.Child

interface HistoryUseCase {
    fun getHistory(): Flow<PagingData<Child>>

}