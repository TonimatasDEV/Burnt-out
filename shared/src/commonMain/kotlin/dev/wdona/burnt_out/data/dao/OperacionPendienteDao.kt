package dev.wdona.burnt_out.data.dao

import dev.wdona.burnt_out.domain.model.OperacionPendiente
import dev.wdona.burntout.shared.db.OperacionPendienteEntity

interface OperacionPendienteDao {
    suspend fun getOperacionesPendientes(): List<OperacionPendiente>;
    suspend fun insertOperacionPendiente(tipoAccion: String, tablaAfectada: String, idAfectado: Long, datosJson: String, timestampCreacion: Long, sincronizado: Long = 0L);
    suspend fun deleteOperacionPendientePorEstado(estado: Long);
    suspend fun cambiarEstadoOperacion(sincronizado: Long, idOperacion: Long);
    suspend fun deleteOperacionPendiente(idOperacion: Long);

}