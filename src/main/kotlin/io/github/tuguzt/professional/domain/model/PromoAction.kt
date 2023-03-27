package io.github.tuguzt.professional.domain.model

@JvmInline
value class PromoActionId(val value: Long)

data class PromoAction(
    val id: PromoActionId,
    val name: String,
    val description: String,
    val prizes: List<Prize>,
    val participants: List<Participant>,
)
