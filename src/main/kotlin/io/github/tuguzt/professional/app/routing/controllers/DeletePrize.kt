package io.github.tuguzt.professional.app.routing.controllers

import io.github.tuguzt.professional.app.routing.Promo
import io.github.tuguzt.professional.domain.model.PrizeId
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.usecase.DeletePrize
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.deletePrize() {
    val interactor: DeletePrize by inject()

    delete<Promo.Id.Prize.Id> {
        val promoId = PromoActionId(it.parent.parent.promoId)
        val id = PrizeId(it.prizeId)
        interactor.deletePrize(promoId, id)
        call.respond(HttpStatusCode.NoContent)
    }
}
