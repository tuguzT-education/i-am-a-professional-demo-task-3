package io.github.tuguzt.professional.domain.model

@JvmInline
value class PrizeId(val value: Long)

data class Prize(
    val id: PrizeId,
    val description: String,
)
