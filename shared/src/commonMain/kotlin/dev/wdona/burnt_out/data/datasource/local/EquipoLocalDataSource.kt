package dev.wdona.burnt_out.data.datasource.local

import dev.wdona.burnt_out.shared.domain.Equipo

interface EquipoLocalDataSource {
    suspend fun getEquipoById(idEquipo: Long): Equipo
    suspend fun getEquiposByOrg(idOrg: Long): List<Equipo>
    suspend fun crearEquipo(equipo: Equipo): Long
    suspend fun actualizarEquipo(equipo: Equipo): Boolean
    suspend fun eliminarEquipo(idEquipo: Long): Boolean
    suspend fun insertOrUpdateEquipo(equipo: Equipo): Boolean
    suspend fun updatePuntuacion(idEquipo: Long, puntos: Long): Boolean
    suspend fun eliminarEquiposPorOrg(idOrg: Long)
}
