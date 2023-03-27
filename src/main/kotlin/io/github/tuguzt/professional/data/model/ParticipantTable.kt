package io.github.tuguzt.professional.data.model

import org.jetbrains.exposed.dao.id.LongIdTable

object ParticipantTable : LongIdTable() {
    val name = text("name")
    val promoAction = reference("promoAction", PromoActionTable)
}
