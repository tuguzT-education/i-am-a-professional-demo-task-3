package io.github.tuguzt.professional.app.di

import io.github.tuguzt.professional.data.client.RepositoryClient
import io.github.tuguzt.professional.data.repository.ParticipantRepositoryImpl
import io.github.tuguzt.professional.data.repository.PrizeRepositoryImpl
import io.github.tuguzt.professional.data.repository.PromoActionRepositoryImpl
import io.github.tuguzt.professional.domain.repository.ParticipantRepository
import io.github.tuguzt.professional.domain.repository.PrizeRepository
import io.github.tuguzt.professional.domain.repository.PromoActionRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun createDataModule(databaseUri: String, driverClassName: String): Module = module {
    single {
        RepositoryClient(databaseUri, driverClassName)
    }
    factory<PromoActionRepository> {
        val client = get<RepositoryClient>()
        PromoActionRepositoryImpl(client)
    }
    factory<ParticipantRepository> {
        val client = get<RepositoryClient>()
        ParticipantRepositoryImpl(client)
    }
    factory<PrizeRepository> {
        val client = get<RepositoryClient>()
        PrizeRepositoryImpl(client)
    }
}
