package io.github.tuguzt.professional.app.model

import io.github.tuguzt.professional.domain.model.Participant
import io.github.tuguzt.professional.domain.model.Prize
import io.github.tuguzt.professional.domain.model.PromoAction
import kotlinx.serialization.Serializable

@Serializable
data class NewPromoActionData(
    val name: String,
    val description: String?,
)

@Serializable
data class UpdatePromoActionData(
    val name: String,
    val description: String,
)

@Serializable
data class PromoActionSummaryData(
    val id: Long,
    val name: String,
    val description: String,
)

@Serializable
data class PromoActionData(
    val id: Long,
    val name: String,
    val description: String,
    val prizes: List<PrizeData>,
    val participants: List<ParticipantData>,
)

fun PromoAction.toData(): PromoActionData = PromoActionData(
    id = id.value,
    name = name,
    description = description,
    prizes = prizes.map(Prize::toData),
    participants = participants.map(Participant::toData),
)
