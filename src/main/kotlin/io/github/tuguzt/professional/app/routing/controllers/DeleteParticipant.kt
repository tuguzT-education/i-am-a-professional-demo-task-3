package io.github.tuguzt.professional.app.routing.controllers

import io.github.tuguzt.professional.app.routing.Promo
import io.github.tuguzt.professional.domain.model.ParticipantId
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.usecase.DeleteParticipant
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.deleteParticipant() {
    val interactor: DeleteParticipant by inject()

    delete<Promo.Id.Participant.Id> {
        val promoId = PromoActionId(it.parent.parent.promoId)
        val id = ParticipantId(it.participantId)
        interactor.deleteParticipant(promoId, id)
        call.respond(HttpStatusCode.NoContent)
    }
}
