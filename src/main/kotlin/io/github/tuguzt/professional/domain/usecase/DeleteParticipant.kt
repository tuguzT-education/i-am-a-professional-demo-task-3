package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.ParticipantId
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.repository.ParticipantRepository

class DeleteParticipant(
    private val repository: ParticipantRepository,
) {
    suspend fun deleteParticipant(promoId: PromoActionId, id: ParticipantId) {
        repository.delete(promoId, id)
    }
}
