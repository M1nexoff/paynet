package uz.gita.m1nex.usecase.card

import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.card.AddCard
import uz.gita.m1nex.entity.data.model.request.AddCardRequest
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.core.data.model.card.UpdateCardRequest

interface CardUseCase {
    fun addCard(addCard: AddCard): Flow<ResultData<Unit>>
    fun getCards(): Flow<ResultData<List<CardData>>>
    fun deleteCard(int: String): Flow<ResultData<Unit>>
    fun updateCard(updateCard: UpdateCardRequest): Flow<ResultData<Unit>>
}