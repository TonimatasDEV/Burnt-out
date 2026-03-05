package dev.wdona.burnt_out.data.dao.impl

import dev.wdona.burnt_out.data.dao.EquipoDao
import dev.wdona.burnt_out.shared.db.AppDatabase

class EquipoDaoImpl(appDatabase: AppDatabase) : EquipoDao {
    private val queries = appDatabase.appDatabaseQueries
}
