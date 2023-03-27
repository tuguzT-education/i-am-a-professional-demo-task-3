package io.github.tuguzt.professional.data.repository

import io.github.tuguzt.professional.data.client.RepositoryClient
import io.github.tuguzt.professional.data.model.PrizeEntity
import io.github.tuguzt.professional.data.model.PrizeTable
import io.github.tuguzt.professional.data.model.PromoActionEntity
import io.github.tuguzt.professional.domain.model.Prize
import io.github.tuguzt.professional.domain.model.PrizeId
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.repository.PrizeRepository
import io.github.tuguzt.professional.domain.repository.PrizeRepository.NewPrize
import org.jetbrains.exposed.sql.and

class PrizeRepositoryImpl(
    private val client: RepositoryClient,
) : PrizeRepository {
    override suspend fun create(promoId: PromoActionId, new: NewPrize): Prize = client.transaction {
        val promoActionEntity = PromoActionEntity.findById(promoId.value)
        val entity = PrizeEntity.new {
            description = new.description
            promoAction = checkNotNull(promoActionEntity)
        }
        entity.toDomain()
    }

    override suspend fun delete(promoId: PromoActionId, id: PrizeId) = client.transaction {
        val entity = PrizeEntity
            .find {
                val findById = PrizeTable.id eq id.value
                val findByPromoId = PrizeTable.promoAction eq promoId.value
                findById and findByPromoId
            }
            .first()
        entity.delete()
    }
}
