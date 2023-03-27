package io.github.tuguzt.professional.domain.repository

import io.github.tuguzt.professional.domain.model.Prize
import io.github.tuguzt.professional.domain.model.PrizeId
import io.github.tuguzt.professional.domain.model.PromoActionId

interface PrizeRepository {
    data class NewPrize(
        val description: String,
    )

    suspend fun create(promoId: PromoActionId, new: NewPrize): Prize

    suspend fun delete(promoId: PromoActionId, id: PrizeId)
}
