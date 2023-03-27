package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.repository.PromoActionRepository

class DeletePromoAction(
    private val repository: PromoActionRepository,
) {
    suspend fun deletePromoAction(id: PromoActionId) {
        repository.deleteById(id)
    }
}
