package io.github.tuguzt.professional.data.model

import org.jetbrains.exposed.dao.id.LongIdTable

object PromoActionTable : LongIdTable() {
    val name = text("name")
    val description = text("description")
}
