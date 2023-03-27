package io.github.tuguzt.professional.app.di

import io.github.tuguzt.professional.domain.repository.ParticipantRepository
import io.github.tuguzt.professional.domain.repository.PrizeRepository
import io.github.tuguzt.professional.domain.repository.PromoActionRepository
import io.github.tuguzt.professional.domain.usecase.*
import org.koin.core.module.Module
import org.koin.dsl.module

fun createInteractorModule(): Module = module {
    factory {
        val repository = get<PromoActionRepository>()
        CreatePromoAction(repository)
    }
    factory {
        val repository = get<PromoActionRepository>()
        GetAllPromoActions(repository)
    }
    factory {
        val repository = get<PromoActionRepository>()
        GetPromoAction(repository)
    }
    factory {
        val repository = get<PromoActionRepository>()
        UpdatePromoAction(repository)
    }
    factory {
        val repository = get<PromoActionRepository>()
        DeletePromoAction(repository)
    }

    factory {
        val repository = get<ParticipantRepository>()
        CreateParticipant(repository)
    }
    factory {
        val repository = get<ParticipantRepository>()
        DeleteParticipant(repository)
    }

    factory {
        val repository = get<PrizeRepository>()
        CreatePrize(repository)
    }
    factory {
        val repository = get<PrizeRepository>()
        DeletePrize(repository)
    }

    factory {
        val repository = get<PromoActionRepository>()
        RafflePromoAction(repository)
    }
}
