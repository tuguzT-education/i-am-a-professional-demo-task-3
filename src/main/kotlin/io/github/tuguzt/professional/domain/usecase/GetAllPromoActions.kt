package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.repository.PromoActionRepository

class GetAllPromoActions(
    private val repository: PromoActionRepository,
) {
    suspend fun getAllPromoActions(): List<PromoActionSummary> {
        val allPromoActions = repository.getAll()
        val summary = allPromoActions.map { promoAction ->
            PromoActionSummary(
                id = promoAction.id,
                name = promoAction.name,
                description = promoAction.description,
            )
        }
        return summary
    }

    data class PromoActionSummary(
        val id: PromoActionId,
        val name: String,
        val description: String,
    )
}
