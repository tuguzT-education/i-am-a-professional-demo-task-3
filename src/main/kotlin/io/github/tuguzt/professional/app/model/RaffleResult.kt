package io.github.tuguzt.professional.app.model

import io.github.tuguzt.professional.domain.model.RaffleResult
import kotlinx.serialization.Serializable

@Serializable
data class RaffleResultData(
    val winner: ParticipantData,
    val prize: PrizeData,
)

fun RaffleResult.toData(): RaffleResultData = RaffleResultData(
    winner = winner.toData(),
    prize = prize.toData(),
)
