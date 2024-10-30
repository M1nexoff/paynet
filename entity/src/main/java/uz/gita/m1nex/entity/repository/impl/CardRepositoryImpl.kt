package uz.gita.m1nex.entity.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.card.AddCard
import uz.gita.m1nex.entity.data.model.request.AddCardRequest
import uz.gita.m1nex.core.data.model.card.CardData
import uz.gita.m1nex.entity.data.model.request.UpdateCardRequest
import uz.gita.m1nex.core.withContextSafety
import uz.gita.m1nex.entity.data.local.LocalStorage
import uz.gita.m1nex.entity.data.remote.CardApi
import uz.gita.m1nex.entity.data.util.mapTo
import uz.gita.m1nex.entity.data.util.toRequest
import uz.gita.m1nex.entity.data.util.toResultData
import uz.gita.m1nex.entity.repository.CardRepository
import javax.inject.Inject

internal class CardRepositoryImpl @Inject constructor(
    private val cardApi: CardApi,
    private val localStorage: LocalStorage,
    private val gson: Gson
) : CardRepository {
    override suspend fun getCards(): ResultData<List<CardData>> = withContextSafety(Dispatchers.IO){
        cardApi.getCards()
            .toResultData()
            .mapTo {
                localStorage.cards = gson.toJson(it)
                it
            }
    }

    override suspend fun addCard(addCard: AddCard): ResultData<Unit> = withContextSafety(Dispatchers.IO){
        cardApi.addCard(addCard.toRequest())
            .toResultData()
            .mapTo {

            }
    }

    override suspend fun updateCard(updateCard: UpdateCardRequest): ResultData<Unit> = withContextSafety(Dispatchers.IO){
        cardApi.updateCard(updateCard)
            .toResultData()
            .mapTo {  }
    }

    override suspend fun deleteCard(cardId: Int): ResultData<Unit> = withContextSafety(Dispatchers.IO){
        cardApi.deleteCard(cardId)
            .toResultData()
            .mapTo {  }
    }


}