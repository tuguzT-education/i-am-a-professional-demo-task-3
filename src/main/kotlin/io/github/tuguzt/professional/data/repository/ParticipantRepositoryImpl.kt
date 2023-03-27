package io.github.tuguzt.professional.data.repository

import io.github.tuguzt.professional.data.client.RepositoryClient
import io.github.tuguzt.professional.data.model.ParticipantEntity
import io.github.tuguzt.professional.data.model.ParticipantTable
import io.github.tuguzt.professional.data.model.PromoActionEntity
import io.github.tuguzt.professional.domain.model.Participant
import io.github.tuguzt.professional.domain.model.ParticipantId
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.repository.ParticipantRepository
import io.github.tuguzt.professional.domain.repository.ParticipantRepository.NewParticipant
import org.jetbrains.exposed.sql.and

class ParticipantRepositoryImpl(
    private val client: RepositoryClient,
) : ParticipantRepository {
    override suspend fun create(promoId: PromoActionId, new: NewParticipant): Participant = client.transaction {
        val promoActionEntity = PromoActionEntity.findById(promoId.value)
        val entity = ParticipantEntity.new {
            name = new.name
            promoAction = checkNotNull(promoActionEntity)
        }
        entity.toDomain()
    }

    override suspend fun delete(promoId: PromoActionId, id: ParticipantId) = client.transaction {
        val entity = ParticipantEntity
            .find {
                val findById = ParticipantTable.id eq id.value
                val findByPromoId = ParticipantTable.promoAction eq promoId.value
                findById and findByPromoId
            }
            .first()
        entity.delete()
    }
}
