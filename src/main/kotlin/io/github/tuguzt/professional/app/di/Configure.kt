package io.github.tuguzt.professional.app.di

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {
    val databaseUri = environment.config.property("database.uri").getString()
    val driverClassName = environment.config.property("database.driver").getString()

    val interactorModule = createInteractorModule()
    val dataModule = createDataModule(databaseUri, driverClassName)
    install(Koin) {
        slf4jLogger()
        modules(interactorModule, dataModule)
    }
}
