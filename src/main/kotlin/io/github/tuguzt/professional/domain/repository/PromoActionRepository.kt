package io.github.tuguzt.professional.domain.repository

import io.github.tuguzt.professional.domain.model.PromoAction
import io.github.tuguzt.professional.domain.model.PromoActionId

interface PromoActionRepository {
    data class PromoActionDetails(
        val name: String,
        val description: String,
    )

    suspend fun getAll(): List<PromoAction>

    suspend fun create(new: PromoActionDetails): PromoAction

    suspend fun getById(id: PromoActionId): PromoAction?

    suspend fun update(id: PromoActionId, update: PromoActionDetails)

    suspend fun deleteById(id: PromoActionId)
}
