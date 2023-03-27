package io.github.tuguzt.professional.data.client

import io.github.tuguzt.professional.data.model.ParticipantTable
import io.github.tuguzt.professional.data.model.PrizeTable
import io.github.tuguzt.professional.data.model.PromoActionTable
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class RepositoryClient(databaseUri: String, driverClassName: String) {
    private val database = Database.connect(url = databaseUri, driver = driverClassName)

    init {
        transaction(database) {
            SchemaUtils.create(PromoActionTable, ParticipantTable, PrizeTable)
        }
    }

    suspend fun <T> transaction(statement: suspend Transaction.() -> T): T =
        newSuspendedTransaction(context = Dispatchers.IO, db = database, statement = statement)
}
