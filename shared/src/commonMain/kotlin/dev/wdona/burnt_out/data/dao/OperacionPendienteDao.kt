package dev.wdona.burnt_out.data.dao

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

interface OperacionPendienteDao {
    suspend fun insertOperacionPendiente(operacionPendiente: OperacionPendienteEntity);
    suspend fun getOperacionesPendientes(): List<OperacionPendienteEntity>;
    suspend fun deleteOperacionPendientePorEstado(estado: Long);
    suspend fun cambiarEstadoOperacion(operacionPendiente: OperacionPendienteEntity);
    suspend fun deleteOperacionPendiente(idOperacion: Long);

}