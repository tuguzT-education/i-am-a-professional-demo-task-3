package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.PrizeId
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.repository.PrizeRepository

class CreatePrize(
    private val repository: PrizeRepository,
) {
    @Suppress("NAME_SHADOWING")
    suspend fun postPrize(promoId: PromoActionId, new: NewPrize): PrizeId {
        val new = PrizeRepository.NewPrize(description = new.description)
        val prize = repository.create(promoId, new)
        return prize.id
    }

    data class NewPrize(
        val description: String,
    )
}
