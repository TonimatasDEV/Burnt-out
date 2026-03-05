package dev.wdona.burnt_out.data.dao.impl

import dev.wdona.burnt_out.data.dao.UsuarioDao
import dev.wdona.burnt_out.shared.db.AppDatabase

class UsuarioDaoImpl(appDatabase: AppDatabase) : UsuarioDao {
    private val queries = appDatabase.appDatabaseQueries
}
