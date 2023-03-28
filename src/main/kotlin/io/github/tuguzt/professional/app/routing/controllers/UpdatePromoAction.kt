package io.github.tuguzt.professional.app.routing.controllers

import io.github.tuguzt.professional.app.model.UpdatePromoActionData
import io.github.tuguzt.professional.app.routing.Promo
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.usecase.UpdatePromoAction
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.put
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.updatePromoAction() {
    val interactor: UpdatePromoAction by inject()

    put<Promo.Id> {
        val id = PromoActionId(it.promoId)
        val body = call.receive<UpdatePromoActionData>()
        val update = UpdatePromoAction.Update(
            name = body.name,
            description = body.description,
        )
        interactor.putPromoAction(id, update)
        call.respond(HttpStatusCode.NoContent)
    }
}
