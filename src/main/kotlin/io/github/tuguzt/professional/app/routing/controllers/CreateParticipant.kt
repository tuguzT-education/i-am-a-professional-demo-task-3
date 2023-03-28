package io.github.tuguzt.professional.app.routing.controllers

import io.github.tuguzt.professional.app.model.NewParticipantData
import io.github.tuguzt.professional.app.routing.Promo
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.usecase.CreateParticipant
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.post
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.createParticipant() {
    val interactor: CreateParticipant by inject()

    /**
     * Добавление участника в промоакцию по идентификатору промоакции.
     */
    post<Promo.Id.Participant> {
        val promoId = PromoActionId(it.parent.promoId)
        val body = call.receive<NewParticipantData>()
        val new = CreateParticipant.NewParticipant(name = body.name)
        val id = interactor.postParticipant(promoId, new)
        call.respond(HttpStatusCode.Created, id.value)
    }
}
