package uz.gita.m1nex.entity.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import uz.gita.m1nex.core.data.model.transfer.TransferData
import uz.gita.m1nex.entity.data.model.request.TransferRequests
import uz.gita.m1nex.core.data.model.TransferResponses
import uz.gita.m1nex.entity.data.local.ForceLocalCache
import uz.gita.m1nex.entity.data.local.ForceNetwork
import uz.gita.m1nex.entity.data.local.LocalCacheControl

internal interface TransferApi {

    @POST("v1/transfer/card-owner")
    suspend fun getCardOwnerByPan(@Body request: TransferRequests.GetCardOwnerByPan): Response<TransferResponses.GetCardOwnerByPan>

    @POST("v1/transfer/fee")
    suspend fun getFee(@Body request: TransferRequests.GetFee): Response<TransferResponses.GetFee>

    @POST("v1/transfer/transfer")
    suspend fun transfer(@Body request: TransferRequests.TransferRequest): Response<TransferResponses.TransferResponse>

    @POST("v1/transfer/transfer/verify")
    suspend fun transferVerify(@Body request: TransferRequests.TransferVerify): Response<TransferResponses.TransferVerify>

    @POST("v1/transfer/transfer/resend")
    suspend fun transferResend(@Body request: TransferRequests.TransferResend): Response<TransferResponses.TransferResend>

    @ForceNetwork
    @GET("v1/transfer/history")
    suspend fun getHistory(
        @Query("size") size: Int,
        @Query("current-page") currentPage: Int
    ): Response<TransferResponses.GetHistory>

    @ForceLocalCache
    @GET("v1/transfer/history")
    suspend fun getHistoryCache(
        @LocalCacheControl
        @Query("size") size: Int,
        @LocalCacheControl
        @Query("current-page") currentPage: Int
    ): Response<TransferResponses.GetHistory>
}