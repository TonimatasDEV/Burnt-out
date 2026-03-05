package dev.wdona.burnt_out.domain.repository

import dev.wdona.burnt_out.shared.domain.Equipo
import dev.wdona.burnt_out.shared.domain.Usuario

interface EquipoRepository {
    suspend fun getEquiposByOrg(idOrg: Long): List<Equipo>
    suspend fun getEquipoById(idEquipo: Long): Equipo?
    suspend fun crearEquipo(equipo: Equipo)
    suspend fun actualizarEquipo(equipo: Equipo)
    suspend fun eliminarEquipo(idEquipo: Long)
    suspend fun getMiembrosEquipo(idEquipo: Long): List<Usuario>
    suspend fun updatePuntuacion(idEquipo: Long, puntos: Long)
}
