package io.github.tuguzt.professional.data.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ParticipantEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ParticipantEntity>(ParticipantTable)

    var name by ParticipantTable.name
    var promoAction by PromoActionEntity referencedOn ParticipantTable.promoAction
}
