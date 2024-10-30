package uz.gita.m1nex.entity.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.gita.m1nex.entity.data.model.respone.AddCard
import uz.gita.m1nex.entity.data.model.request.AddCardRequest
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.entity.data.model.respone.DeleteCard
import uz.gita.m1nex.entity.data.model.respone.UpdateCard
import uz.gita.m1nex.entity.data.model.request.UpdateCardRequest

internal interface CardApi {


    @GET("v1/card")
    suspend fun getCards(): Response<List<CardData>>

    @POST("v1/card")
    suspend fun addCard(@Body request: AddCardRequest): Response<AddCard>

    @PUT("v1/card")
    suspend fun updateCard(@Body request: UpdateCardRequest): Response<UpdateCard>

    @DELETE("v1/card/{id}")
    suspend fun deleteCard(@Path("id") cardId: Int): Response<DeleteCard>


}
suspend fun getCardss(): List<CardData> {
    return listOf(
        CardData(
            id = "1",
            name = "Card One",
            amount = 100000,
            owner = "John Doe",
            pan = "1234 5678 9012 3456",
            expiredYear = 2025,
            expiredMonth = 12,
            themeType = 1,
            isVisible = true
        ),
        CardData(
            id = "2",
            name = "Card Two",
            amount = 100000,
            owner = "Alice Smith",
            pan = "2345 6789 0123 4567",
            expiredYear = 2026,
            expiredMonth = 11,
            themeType = 2,
            isVisible = true
        ),
        CardData(
            id = "3",
            name = "Card Three",
            amount = 100000,
            owner = "Bob Johnson",
            pan = "3456 7890 1234 5678",
            expiredYear = 2027,
            expiredMonth = 10,
            themeType = 1,
            isVisible = false
        ),
        CardData(
            id = "4",
            name = "Card Four",
            amount = 100000,
            owner = "Eve Adams",
            pan = "4567 8901 2345 6789",
            expiredYear = 2024,
            expiredMonth = 9,
            themeType = 3,
            isVisible = true
        ),
        CardData(
            id = "5",
            name = "Card Five",
            amount = 100000,
            owner = "Charles Wilson",
            pan = "5678 9012 3456 7890",
            expiredYear = 2028,
            expiredMonth = 8,
            themeType = 2,
            isVisible = false
        )
    )
}


fun balance():Int{
    return 500000
}
