package io.github.tuguzt.professional.app.routing.controllers

import io.github.tuguzt.professional.app.model.NewPrizeData
import io.github.tuguzt.professional.app.routing.Promo
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.usecase.CreatePrize
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.createPrize() {
    val interactor: CreatePrize by inject()

    /**
     * Добавление приза в промоакцию по идентификатору промоакции.
     */
    post<Promo.Id.Prize> {
        val promoId = PromoActionId(it.parent.promoId)
        val body = call.receive<NewPrizeData>()
        val new = CreatePrize.NewPrize(description = body.description)
        val id = interactor.postPrize(promoId, new)
        call.respond(HttpStatusCode.Created, id.value)
    }
}
