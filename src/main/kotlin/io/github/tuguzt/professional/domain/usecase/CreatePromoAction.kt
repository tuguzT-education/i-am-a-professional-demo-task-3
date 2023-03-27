package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.repository.PromoActionRepository
import io.github.tuguzt.professional.domain.repository.PromoActionRepository.PromoActionDetails

class CreatePromoAction(
    private val repository: PromoActionRepository,
) {
    suspend fun postPromoAction(body: NewPromoAction): PromoActionId {
        val promoActionDetails = PromoActionDetails(
            name = body.name,
            description = body.description.orEmpty(),
        )
        val promoAction = repository.create(promoActionDetails)
        return promoAction.id
    }

    data class NewPromoAction(
        val name: String,
        val description: String?,
    )
}
