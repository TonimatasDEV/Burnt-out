package dev.wdona.burnt_out.shared.db

import app.cash.sqldelight.db.SqlDriver

object DatabaseInit {
    private var database: AppDatabase? = null

    fun init(driver: SqlDriver) {
        if (database == null) {
            database = AppDatabase(driver)
            try {
                database?.appDatabaseQueries?.getOrganizacionById(1)?.executeAsOneOrNull()
            } catch (e: Exception) {
            }
        }
    }

    fun getDatabase(): AppDatabase {
        return database ?: throw IllegalStateException("Database not initialized. Call init() first.")
    }
}
