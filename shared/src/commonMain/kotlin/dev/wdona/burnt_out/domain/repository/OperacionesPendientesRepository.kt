package dev.wdona.burnt_out.domain.repository

import dev.wdona.burnt_out.domain.model.OperacionPendiente


interface OperacionesPendientesRepository {
    suspend fun agregarOperacionPendiente(
        tipoAccion: String,
        tablaAfectada: String,
        idAfectado: Long,
        datosJson: String
    );

    suspend fun obtenerOperacionesPendientes(): List<OperacionPendiente>

    suspend fun marcarOperacionComoSincronizada(idAccion: Long)

}