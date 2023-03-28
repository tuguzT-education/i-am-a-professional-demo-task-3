package io.github.tuguzt.professional.app.routing.controllers

import io.github.tuguzt.professional.app.model.toData
import io.github.tuguzt.professional.app.routing.Promo
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.model.RaffleResult
import io.github.tuguzt.professional.domain.usecase.RafflePromoAction
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.rafflePromoAction() {
    val interactor: RafflePromoAction by inject()

    /**
     * Проведение розыгрыша призов в промоакции по идентификатору промоакции.
     *
     * Проведение розыгрыша возможно только в том случае, когда
     * количество участников и призов в промоакции совпадает
     * (т.е., например, если в промоакции в текущий момент
     * 2 участника и 2 приза или 3 участника и 3 приза и т.д.).
     */
    post<Promo.Id.Raffle> {
        val id = PromoActionId(it.parent.promoId)
        try {
            val results = interactor.postRaffleResult(id).map(RaffleResult::toData)
            call.respond(HttpStatusCode.OK, results)
        } catch (e: RafflePromoAction.Exception) {
            call.respond(HttpStatusCode.Conflict)
        }
    }
}
