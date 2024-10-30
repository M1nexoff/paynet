package uz.gita.m1nex.usecase.card

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.card.AddCard
import uz.gita.m1nex.entity.data.model.request.AddCardRequest
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.entity.data.model.request.UpdateCardRequest
import uz.gita.m1nex.core.flowWithCatch
import uz.gita.m1nex.entity.repository.CacheRepository
import uz.gita.m1nex.entity.repository.CardRepository
import javax.inject.Inject

class CardUseCaseImpl @Inject constructor(
    private val cardRepository: CardRepository,
    private val cacheRepository: CacheRepository
) : CardUseCase {
    override fun addCard(addCard: AddCard): Flow<ResultData<Unit>> = flowWithCatch{
        val result = cardRepository.addCard(addCard)
        emit(result)
    }

    override fun getCards(): Flow<ResultData<List<CardData>>> = flowWithCatch{
        val result1 = cacheRepository.getCards()
        emit(result1)
        val result = cardRepository.getCards()
        emit(result)
    }

    override fun deleteCard(id: Int): Flow<ResultData<Unit>> = flowWithCatch{
        val result = cardRepository.deleteCard(id)
        emit(result)
    }

    override fun updateCard(updateCard: UpdateCardRequest): Flow<ResultData<Unit>> = flowWithCatch{
        val result = cardRepository.updateCard(updateCard)
        emit(result)
    }

}