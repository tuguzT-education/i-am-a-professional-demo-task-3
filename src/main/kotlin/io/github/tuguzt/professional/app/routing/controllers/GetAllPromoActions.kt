package io.github.tuguzt.professional.app.routing.controllers

import io.github.tuguzt.professional.app.model.PromoActionSummaryData
import io.github.tuguzt.professional.app.routing.Promo
import io.github.tuguzt.professional.domain.usecase.GetAllPromoActions
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllPromoActions() {
    val interactor: GetAllPromoActions by inject()

    get<Promo> {
        val summary = interactor.getAllPromoActions().map { (id, name, description) ->
            PromoActionSummaryData(id.value, name, description)
        }
        call.respond(HttpStatusCode.OK, summary)
    }
}
