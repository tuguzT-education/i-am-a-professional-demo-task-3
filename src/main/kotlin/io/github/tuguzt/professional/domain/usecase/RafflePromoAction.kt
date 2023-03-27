package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.model.RaffleResult
import io.github.tuguzt.professional.domain.repository.PromoActionRepository
import kotlin.random.Random

class RafflePromoAction(
    private val repository: PromoActionRepository,
    private val random: Random = Random.Default,
) {
    suspend fun postRaffleResult(id: PromoActionId): List<RaffleResult> {
        val promo = repository.getById(id)
        checkNotNull(promo) { "Promo action does not exist by provided id" }
        if (promo.prizes.size != promo.participants.size) {
            throw Exception()
        }

        val participants = promo.participants.shuffled(random)
        val prizes = promo.prizes.shuffled(random)
        val raffleResults = (participants zip prizes).map { (winner, prize) ->
            RaffleResult(winner, prize)
        }
        return raffleResults
    }

    class Exception : IllegalStateException("Count of prizes and participants should be equal")
}
