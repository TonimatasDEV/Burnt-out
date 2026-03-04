package dev.wdona.burnt_out.data.dao.impl

import dev.wdona.burnt_out.data.dao.OperacionPendienteDao
import dev.wdona.burnt_out.domain.model.OperacionPendiente
import dev.wdona.burnt_out.shared.db.AppDatabase
import dev.wdona.burntout.shared.db.OperacionPendienteEntity

/*
    ID_Accion INTEGER NOT NULL,
    tipo_accion TEXT NOT NULL,
    tabla_afectada TEXT NOT NULL,
    id_afectado INTEGER NOT NULL,
    datos_json TEXT NOT NULL,
    timestamp_creacion INTEGER NOT NULL,
    sincronizado INTEGER DEFAULT 0,
    PRIMARY KEY (ID_Accion)
 */

class OperacionPendienteDaoImpl(appDatabase: AppDatabase) : OperacionPendienteDao {
    private val queries = appDatabase.appDatabaseQueries

    override suspend fun getOperacionesPendientes(): List<OperacionPendienteEntity> {
        return queries.getOperacionesPendientes(50).executeAsList().map {
            Mapper
        }
    }

    override suspend fun insertOperacionPendiente(operacionPendiente: OperacionPendienteEntity) {

    }

    override suspend fun deleteOperacionPendientePorEstado(id: Long) {
    }

    override suspend fun cambiarEstadoOperacion(operacionPendiente: OperacionPendienteEntity) {

    }

    override suspend fun deleteOperacionPendiente(idOperacion: Long) {

    }


}