package uz.gita.m1nex.entity.repository.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.Child
import uz.gita.m1nex.core.getText
import uz.gita.m1nex.core.onFail
import uz.gita.m1nex.core.onSuccess
import uz.gita.m1nex.core.withContextSafety
import uz.gita.m1nex.entity.data.remote.TransferApi
import uz.gita.m1nex.entity.data.util.mapTo
import uz.gita.m1nex.entity.data.util.toResultData
import uz.gita.m1nex.entity.repository.HistoryRepository
import javax.inject.Inject

internal class HistoryRepositoryImpl @Inject constructor(
    private val api: TransferApi
) : HistoryRepository {
    override suspend fun getHistory(): Flow<PagingData<Child>> = Pager(
            config = PagingConfig(10),
            pagingSourceFactory = { TestPaginationSource(api) }
        ).flow
}

internal class TestPaginationSource(private val api: TransferApi) : PagingSource<Int, Child>() {

    override fun getRefreshKey(state: PagingState<Int, Child>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1) ?: state.closestPageToPosition(
                anchor
            )?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Child> {
        val page = params.key ?: 1
        api.getHistory(10, page).toResultData().onSuccess {
            return LoadResult.Page(
                data = child,
                nextKey = if (totalPages > page) page.plus(1) else null,
                prevKey = if (page > 1) page.minus(1) else null
            )
        }.onFail {
            return LoadResult.Error(
                Throwable(this.message.toString())
            )
        }
        return LoadResult.Error(
            Exception("Unknown Error!!")
        )
    }

}

@OptIn(ExperimentalPagingApi::class)
internal class MoviesRemoteMediator @Inject constructor(
    private val transferApi: TransferApi
) : RemoteMediator<Int, Child>() {

    private var currentPage = 1
    private var cacheLoaded = false

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Child>
    ): MediatorResult {
        // Determine the current page number
        currentPage = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> currentPage + 1
        }

        return try {
            // First load from cache, then wait and load from network
            val response = if (!cacheLoaded) {
                cacheLoaded = true // Mark that cache has been loaded
                transferApi.getHistoryCache(state.config.pageSize, currentPage)
            } else {
                // Wait 10 seconds before refreshing data from the network
                delay(3000)
                transferApi.getHistory(state.config.pageSize, currentPage)
            }

            // Handle the API response
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    MediatorResult.Success(endOfPaginationReached = data.totalPages <= currentPage)
                } ?: MediatorResult.Success(endOfPaginationReached = true)
            } else {
                MediatorResult.Error(Exception("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
