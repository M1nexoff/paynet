package uz.gita.m1nex.entity.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import uz.gita.m1nex.entity.data.model.request.UpdateInfoRequest
import uz.gita.m1nex.entity.data.model.respone.BasicInfoResponse
import uz.gita.m1nex.entity.data.model.respone.FullInfoResponse
import uz.gita.m1nex.entity.data.model.respone.LastTransfersResponse
import uz.gita.m1nex.entity.data.model.respone.TotalBalanceResponse
import uz.gita.m1nex.entity.data.model.respone.UpdateInfoResponse

internal interface HomeApi {
    @GET("v1/home/total-balance")
    suspend fun getTotalBalance(): Response<TotalBalanceResponse>

    @GET("v1/home/user-info")
    suspend fun getBasicInfo(): Response<BasicInfoResponse>

    @GET("v1/home/user-info/details")
    suspend fun getFullInfo(): Response<FullInfoResponse>

    @PUT("v1/home/user-info")
    suspend fun updateUserInfo(
        @Body updateInfoRequest: UpdateInfoRequest
    ): Response<UpdateInfoResponse>

    @GET("v1/home/last-transfers")
    suspend fun getLastTransfers(): Response<List<LastTransfersResponse>>
}
