package io.github.tuguzt.professional.app.routing.controllers

import io.github.tuguzt.professional.app.routing.Promo
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.usecase.DeletePromoAction
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.deletePromoAction() {
    val interactor: DeletePromoAction by inject()

    /**
     * Удаление промоакции по идентификатору.
     */
    delete<Promo.Id> {
        val id = PromoActionId(it.promoId)
        interactor.deletePromoAction(id)
        call.respond(HttpStatusCode.NoContent)
    }
}
