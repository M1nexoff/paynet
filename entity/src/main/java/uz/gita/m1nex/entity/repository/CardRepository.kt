package uz.gita.m1nex.entity.repository

import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.card.AddCard
import uz.gita.m1nex.entity.data.model.request.AddCardRequest
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.entity.data.model.request.UpdateCardRequest

interface CardRepository {

    suspend fun getCards(): ResultData<List<CardData>>
    suspend fun addCard(addCard: AddCard): ResultData<Unit>
    suspend fun updateCard(updateCard: UpdateCardRequest): ResultData<Unit>
    suspend fun deleteCard(cardId: Int): ResultData<Unit>
    
}