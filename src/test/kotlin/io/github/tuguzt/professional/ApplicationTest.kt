package io.github.tuguzt.professional

import io.github.tuguzt.professional.app.di.configureDI
import io.github.tuguzt.professional.app.model.configureModel
import io.github.tuguzt.professional.app.routing.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureDI()
            configureModel()
            configureRouting()
        }
        client.get("/promo").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("[]", bodyAsText())
        }
    }
}
