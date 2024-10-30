package uz.gita.m1nex.usecase.history

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.Child
import uz.gita.m1nex.core.flowWithCatch
import uz.gita.m1nex.entity.repository.HistoryRepository
import javax.inject.Inject

class HistoryUseCaseImpl @Inject constructor(
    private val repository: HistoryRepository
) : HistoryUseCase {
    override fun getHistory(): Flow<PagingData<Child>> = channelFlow{
        repository.getHistory().onEach {
            trySend(it)
        }.launchIn(this)
    }
}