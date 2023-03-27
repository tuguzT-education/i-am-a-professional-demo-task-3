package io.github.tuguzt.professional.domain.repository

import io.github.tuguzt.professional.domain.model.Participant
import io.github.tuguzt.professional.domain.model.ParticipantId
import io.github.tuguzt.professional.domain.model.PromoActionId

interface ParticipantRepository {
    data class NewParticipant(
        val name: String,
    )

    suspend fun create(promoId: PromoActionId, new: NewParticipant): Participant

    suspend fun delete(promoId: PromoActionId, id: ParticipantId)
}
