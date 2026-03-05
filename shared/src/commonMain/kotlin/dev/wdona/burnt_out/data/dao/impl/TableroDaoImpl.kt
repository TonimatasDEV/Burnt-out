package dev.wdona.burnt_out.data.dao.impl

import dev.wdona.burnt_out.data.dao.TableroDao
import dev.wdona.burnt_out.shared.db.AppDatabase

class TableroDaoImpl(appDatabase: AppDatabase) : TableroDao {
    private val queries = appDatabase.appDatabaseQueries
}
