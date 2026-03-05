package dev.wdona.burnt_out.data.dao

import dev.wdona.burnt_out.shared.domain.Equipo

interface EquipoDao {
    suspend fun getEquipoById(idEquipo: Long): Equipo?
    suspend fun getEquiposByOrg(idOrg: Long): List<Equipo>
    suspend fun insertEquipo(equipo: Equipo): Boolean
    suspend fun updateEquipo(equipo: Equipo): Boolean
    suspend fun deleteEquipo(idEquipo: Long): Boolean
    suspend fun updatePuntuacion(idEquipo: Long, puntos: Long): Boolean
}
