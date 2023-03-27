package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.repository.PromoActionRepository
import io.github.tuguzt.professional.domain.repository.PromoActionRepository.PromoActionDetails

class UpdatePromoAction(
    private val repository: PromoActionRepository,
) {
    suspend fun putPromoAction(id: PromoActionId, update: Update) {
        val promoActionDetails = PromoActionDetails(
            name = update.name,
            description = update.description,
        )
        repository.update(id, promoActionDetails)
    }

    data class Update(
        val name: String,
        val description: String,
    )
}
