package io.github.tuguzt.professional.app.model

import io.github.tuguzt.professional.domain.model.Participant
import kotlinx.serialization.Serializable

@Serializable
data class NewParticipantData(
    val name: String,
)

@Serializable
data class ParticipantData(
    val id: Long,
    val name: String,
)

fun Participant.toData(): ParticipantData = ParticipantData(
    id = id.value,
    name = name,
)
