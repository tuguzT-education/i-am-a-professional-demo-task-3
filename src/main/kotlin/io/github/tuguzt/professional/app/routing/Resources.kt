package io.github.tuguzt.professional.app.routing

import io.ktor.resources.*

@Resource("/promo")
class Promo {
    @Resource("{promoId}")
    class Id(val parent: Promo, val promoId: Long) {
        @Resource("participant")
        class Participant(val parent: Promo.Id) {
            @Resource("{participantId}")
            class Id(val parent: Participant, val participantId: Long)
        }

        @Resource("prize")
        class Prize(val parent: Promo.Id) {
            @Resource("{prizeId}")
            class Id(val parent: Prize, val prizeId: Long)
        }

        @Resource("raffle")
        class Raffle(val parent: Id)
    }
}
