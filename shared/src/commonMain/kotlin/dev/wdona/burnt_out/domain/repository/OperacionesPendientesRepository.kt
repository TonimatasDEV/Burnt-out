package dev.wdona.burnt_out.domain.repository

import dev.wdona.burnt_out.domain.model.OperacionPendiente


interface OperacionesPendientesRepository {
    suspend fun getOperacionesPendientes(): List<OperacionPendiente>;
    suspend fun insertOperacionPendiente(tipoAccion: String, tablaAfectada: String, idAfectado: Long, datosJson: String, timestampCreacion: Long);
    suspend fun deleteOperacionPendientePorEstado(estado: Long);
    suspend fun cambiarEstadoOperacion(sincronizado: Long, idOperacion: Long);
    suspend fun deleteOperacionPendiente(idOperacion: Long);

}