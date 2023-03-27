package io.github.tuguzt.professional.app.model

import io.github.tuguzt.professional.domain.model.Prize
import kotlinx.serialization.Serializable

@Serializable
data class NewPrizeData(
    val description: String,
)

@Serializable
data class PrizeData(
    val id: Long,
    val description: String,
)

fun Prize.toData(): PrizeData = PrizeData(
    id = id.value,
    description = description,
)
