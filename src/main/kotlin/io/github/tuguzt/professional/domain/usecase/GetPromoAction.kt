package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.PromoAction
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.repository.PromoActionRepository

class GetPromoAction(
    private val repository: PromoActionRepository,
) {
    suspend fun getPromoAction(id: PromoActionId): PromoAction? {
        return repository.getById(id)
    }
}
