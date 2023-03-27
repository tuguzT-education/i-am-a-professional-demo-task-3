package io.github.tuguzt.professional.data.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PromoActionEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PromoActionEntity>(PromoActionTable)

    var name by PromoActionTable.name
    var description by PromoActionTable.description

    val prizes by PrizeEntity referrersOn PrizeTable.promoAction
    val participants by ParticipantEntity referrersOn ParticipantTable.promoAction
}
