package io.github.tuguzt.professional.app.routing

import io.github.tuguzt.professional.app.model.*
import io.github.tuguzt.professional.domain.model.ParticipantId
import io.github.tuguzt.professional.domain.model.PrizeId
import io.github.tuguzt.professional.domain.model.PromoActionId
import io.github.tuguzt.professional.domain.model.RaffleResult
import io.github.tuguzt.professional.domain.usecase.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    install(Resources)

    routing {
        openAPI(path = "openapi")
        swaggerUI(path = "swagger")

        post<Promo> {
            val interactor: CreatePromoAction by this@routing.inject()

            val body = call.receive<NewPromoActionData>()
            val new = CreatePromoAction.NewPromoAction(
                name = body.name,
                description = body.description,
            )
            val id = interactor.postPromoAction(new)
            call.respond(HttpStatusCode.Created, id.value)
        }
        get<Promo> {
            val interactor: GetAllPromoActions by this@routing.inject()

            val summary = interactor.getAllPromoActions().map { (id, name, description) ->
                PromoActionSummaryData(id.value, name, description)
            }
            call.respond(HttpStatusCode.OK, summary)
        }
        get<Promo.Id> {
            val interactor: GetPromoAction by this@routing.inject()

            val id = PromoActionId(it.promoId)
            when (val promoAction = interactor.getPromoAction(id)) {
                null -> call.respond(HttpStatusCode.NotFound)
                else -> call.respond(HttpStatusCode.OK, promoAction.toData())
            }
        }
        put<Promo.Id> {
            val interactor: UpdatePromoAction by this@routing.inject()

            val id = PromoActionId(it.promoId)
            val body = call.receive<UpdatePromoActionData>()
            val update = UpdatePromoAction.Update(
                name = body.name,
                description = body.description,
            )
            interactor.putPromoAction(id, update)
            call.respond(HttpStatusCode.NoContent)
        }
        delete<Promo.Id> {
            val interactor: DeletePromoAction by this@routing.inject()

            val id = PromoActionId(it.promoId)
            interactor.deletePromoAction(id)
            call.respond(HttpStatusCode.NoContent)
        }
        post<Promo.Id.Participant> {
            val interactor: CreateParticipant by this@routing.inject()

            val promoId = PromoActionId(it.parent.promoId)
            val body = call.receive<NewParticipantData>()
            val new = CreateParticipant.NewParticipant(name = body.name)
            val id = interactor.postParticipant(promoId, new)
            call.respond(HttpStatusCode.Created, id.value)
        }
        delete<Promo.Id.Participant.Id> {
            val interactor: DeleteParticipant by this@routing.inject()

            val promoId = PromoActionId(it.parent.parent.promoId)
            val id = ParticipantId(it.participantId)
            interactor.deleteParticipant(promoId, id)
            call.respond(HttpStatusCode.NoContent)
        }
        post<Promo.Id.Prize> {
            val interactor: CreatePrize by this@routing.inject()

            val promoId = PromoActionId(it.parent.promoId)
            val body = call.receive<NewPrizeData>()
            val new = CreatePrize.NewPrize(description = body.description)
            val id = interactor.postPrize(promoId, new)
            call.respond(HttpStatusCode.Created, id.value)
        }
        delete<Promo.Id.Prize.Id> {
            val interactor: DeletePrize by this@routing.inject()

            val promoId = PromoActionId(it.parent.parent.promoId)
            val id = PrizeId(it.prizeId)
            interactor.deletePrize(promoId, id)
            call.respond(HttpStatusCode.NoContent)
        }
        post<Promo.Id.Raffle> {
            val interactor: RafflePromoAction by this@routing.inject()

            val id = PromoActionId(it.parent.promoId)
            try {
                val results = interactor.postRaffleResult(id).map(RaffleResult::toData)
                call.respond(HttpStatusCode.OK, results)
            } catch (e: RafflePromoAction.Exception) {
                call.respond(HttpStatusCode.Conflict)
            }
        }
    }
}
