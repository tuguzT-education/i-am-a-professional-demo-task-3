package io.github.tuguzt.professional.app.model

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureModel() {
    install(ContentNegotiation) {
        json()
    }
}
