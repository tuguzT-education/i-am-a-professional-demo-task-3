package io.github.tuguzt.professional.app.routing.controllers

import io.github.tuguzt.professional.app.model.toData
import io.github.tuguzt.professional.app.routing.Promo
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.usecase.GetPromoAction
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getPromoAction() {
    val interactor: GetPromoAction by inject()

    get<Promo.Id> {
        val id = PromoActionId(it.promoId)
        when (val promoAction = interactor.getPromoAction(id)) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(HttpStatusCode.OK, promoAction.toData())
        }
    }
}
