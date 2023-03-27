package io.github.tuguzt.professional.domain.model

@JvmInline
value class ParticipantId(val value: Long)

data class Participant(
    val id: ParticipantId,
    val name: String,
)
