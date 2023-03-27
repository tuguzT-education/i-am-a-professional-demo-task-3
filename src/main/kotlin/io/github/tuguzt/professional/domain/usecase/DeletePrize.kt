package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.PrizeId
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.repository.PrizeRepository

class DeletePrize(
    private val repository: PrizeRepository,
) {
    suspend fun deletePrize(promoId: PromoActionId, id: PrizeId) {
        repository.delete(promoId, id)
    }
}
