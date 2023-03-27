package io.github.tuguzt.professional.data.model

import org.jetbrains.exposed.dao.id.LongIdTable

object PrizeTable : LongIdTable() {
    val description = text("description")
    val promoAction = reference("promoAction", PromoActionTable)
}
