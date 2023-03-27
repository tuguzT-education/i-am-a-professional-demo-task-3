package io.github.tuguzt.professional.data.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PrizeEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PrizeEntity>(PrizeTable)

    var description by PrizeTable.description
    var promoAction by PromoActionEntity referencedOn PrizeTable.promoAction
}
