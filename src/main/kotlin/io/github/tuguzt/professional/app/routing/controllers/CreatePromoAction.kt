package io.github.tuguzt.professional.app.routing.controllers

import io.github.tuguzt.professional.app.model.NewPromoActionData
import io.github.tuguzt.professional.app.routing.Promo
import io.github.tuguzt.professional.domain.usecase.CreatePromoAction
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.postPromoAction() {
    val interactor: CreatePromoAction by inject()

    /**
     * Добавление промоакции с возможностью указания названия (name), описания (description).
     * Описание – не обязательный параметр, название – обязательный.
     */
    post<Promo> {
        val body = call.receive<NewPromoActionData>()
        val new = CreatePromoAction.NewPromoAction(
            name = body.name,
            description = body.description,
        )
        val id = interactor.postPromoAction(new)
        call.respond(HttpStatusCode.Created, id.value)
    }
}
