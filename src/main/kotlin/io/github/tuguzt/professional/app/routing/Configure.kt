package io.github.tuguzt.professional.app.routing

import io.github.tuguzt.professional.app.routing.controllers.*
import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(Resources)

    routing {
        openAPI(path = "openapi")
        swaggerUI(path = "swagger")

        postPromoAction()
        getAllPromoActions()
        getPromoAction()
        updatePromoAction()
        deletePromoAction()

        createParticipant()
        deleteParticipant()

        createPrize()
        deletePrize()

        rafflePromoAction()
    }
}
