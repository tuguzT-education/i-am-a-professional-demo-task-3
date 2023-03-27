package io.github.tuguzt.professional.data.repository

import io.github.tuguzt.professional.data.client.RepositoryClient
import io.github.tuguzt.professional.data.model.PromoActionEntity
import io.github.tuguzt.professional.domain.model.PromoAction
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.repository.PromoActionRepository
import io.github.tuguzt.professional.domain.repository.PromoActionRepository.PromoActionDetails

class PromoActionRepositoryImpl(
    private val client: RepositoryClient,
) : PromoActionRepository {
    override suspend fun getAll(): List<PromoAction> = client.transaction {
        val entities = PromoActionEntity.all()
        entities.map(PromoActionEntity::toDomain)
    }

    override suspend fun create(new: PromoActionDetails): PromoAction = client.transaction {
        val entity = PromoActionEntity.new {
            name = new.name
            description = new.description
        }
        entity.toDomain()
    }

    override suspend fun getById(id: PromoActionId): PromoAction? = client.transaction {
        val entity = PromoActionEntity.findById(id.value)
        entity?.toDomain()
    }

    override suspend fun update(id: PromoActionId, update: PromoActionDetails) = client.transaction {
        val entity = PromoActionEntity.findById(id.value)
        checkNotNull(entity).run {
            name = update.name
            description = update.description
        }
    }

    override suspend fun deleteById(id: PromoActionId) = client.transaction {
        val entity = PromoActionEntity.findById(id.value)
        checkNotNull(entity).delete()
    }
}
