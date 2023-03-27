package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.ParticipantId
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.repository.ParticipantRepository

class CreateParticipant(
    private val repository: ParticipantRepository,
) {
    @Suppress("NAME_SHADOWING")
    suspend fun postParticipant(promoId: PromoActionId, new: NewParticipant): ParticipantId {
        val new = ParticipantRepository.NewParticipant(name = new.name)
        val participant = repository.create(promoId, new)
        return participant.id
    }

    data class NewParticipant(
        val name: String,
    )
}
