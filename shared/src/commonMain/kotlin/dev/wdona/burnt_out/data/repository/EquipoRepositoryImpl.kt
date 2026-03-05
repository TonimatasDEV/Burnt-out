package dev.wdona.burnt_out.data.repository

import dev.wdona.burnt_out.data.datasource.local.EquipoLocalDataSource
import dev.wdona.burnt_out.data.datasource.local.OperacionPendienteLocalDataSource
import dev.wdona.burnt_out.data.datasource.local.UsuarioLocalDataSource
import dev.wdona.burnt_out.data.datasource.mapper.EquipoMapper
import dev.wdona.burnt_out.data.datasource.remote.EquipoRemoteDataSource
import dev.wdona.burnt_out.domain.entity.Entity
import dev.wdona.burnt_out.domain.model.TipoAccion
import dev.wdona.burnt_out.domain.repository.EquipoRepository
import dev.wdona.burnt_out.shared.domain.Equipo
import dev.wdona.burnt_out.shared.domain.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EquipoRepositoryImpl(
    private val local: EquipoLocalDataSource,
    private val remote: EquipoRemoteDataSource,
    private val pendiente: OperacionPendienteLocalDataSource,
    private val usuarioLocal: UsuarioLocalDataSource
) : EquipoRepository {

    private val repositoryScope = CoroutineScope(Dispatchers.Default)

    override suspend fun getEquiposByOrg(idOrg: Long): List<Equipo> {
        repositoryScope.launch {
            try {
                val equipos = remote.getEquiposByOrg(idOrg)
                local.eliminarEquiposPorOrg(idOrg)
                equipos.forEach { local.crearEquipo(it) }
            } catch (e: Exception) {
                println("Servidor offline (getEquiposByOrg): ${e.message}")
            }
        }
        return local.getEquiposByOrg(idOrg)
    }

    override suspend fun getEquipoById(idEquipo: Long): Equipo? {
        repositoryScope.launch {
            try {
                val equipo = remote.getEquipoById(idEquipo)
                local.eliminarEquipo(equipo.idEquipo)
                local.crearEquipo(equipo)
            } catch (e: Exception) {
                println("Servidor offline (getEquipoById): ${e.message}")
            }
        }
        return local.getEquipoById(idEquipo)
    }

    override suspend fun crearEquipo(equipo: Equipo) {
        try {
            local.crearEquipo(equipo)
        } catch (e: Exception) {
            println("Error local al crear equipo: ${e.message}")
        }

        repositoryScope.launch {
            var exito = false
            try {
                exito = remote.crearEquipo(equipo)
            } catch (e: Exception) {
                println("Servidor offline al crear equipo: ${e.message}")
            }

            try {
                pendiente.insertOperacionPendiente(
                    TipoAccion.CREACION.getNombreAccion(),
                    Entity.EQUIPO.getNombreEntity(),
                    equipo.idEquipo,
                    EquipoMapper.toJson(equipo),
                    System.currentTimeMillis(),
                    if (exito) 1L else 0L
                )
            } catch (e: Exception) {
                println("Error al registrar operación pendiente: ${e.message}")
            }
        }
    }

    override suspend fun actualizarEquipo(equipo: Equipo) {
        try {
            local.actualizarEquipo(equipo)
        } catch (e: Exception) {
            println("Error local al actualizar equipo: ${e.message}")
        }

        repositoryScope.launch {
            var exito = false
            try {
                exito = remote.actualizarEquipo(equipo)
            } catch (e: Exception) {
                println("Servidor offline al actualizar equipo: ${e.message}")
            }

            try {
                pendiente.insertOperacionPendiente(
                    TipoAccion.ACTUALIZACION.getNombreAccion(),
                    Entity.EQUIPO.getNombreEntity(),
                    equipo.idEquipo,
                    EquipoMapper.toJson(equipo),
                    System.currentTimeMillis(),
                    if (exito) 1L else 0L
                )
            } catch (e: Exception) {
                println("Error al registrar operación pendiente: ${e.message}")
            }
        }
    }

    override suspend fun eliminarEquipo(idEquipo: Long) {
        try {
            local.eliminarEquipo(idEquipo)
        } catch (e: Exception) {
            println("Error local al eliminar equipo: ${e.message}")
        }

        repositoryScope.launch {
            var exito = false
            try {
                exito = remote.eliminarEquipo(idEquipo)
            } catch (e: Exception) {
                println("Servidor offline al eliminar equipo: ${e.message}")
            }

            try {
                pendiente.insertOperacionPendiente(
                    TipoAccion.ELIMINACION.getNombreAccion(),
                    Entity.EQUIPO.getNombreEntity(),
                    idEquipo,
                    "",
                    System.currentTimeMillis(),
                    if (exito) 1L else 0L
                )
            } catch (e: Exception) {
                println("Error al registrar operación pendiente: ${e.message}")
            }
        }
    }

    override suspend fun getMiembrosEquipo(idEquipo: Long): List<Usuario> {
        repositoryScope.launch {
            try {
                val miembros = remote.getMiembrosEquipo(idEquipo)
                // Aquí se podría actualizar la caché de usuarios si fuera necesario
            } catch (e: Exception) {
                println("Servidor offline (getMiembrosEquipo): ${e.message}")
            }
        }
        return usuarioLocal.getUsuariosByEquipo(idEquipo)
    }

    override suspend fun updatePuntuacion(idEquipo: Long, puntos: Long) {
        try {
            local.updatePuntuacion(idEquipo, puntos)
        } catch (e: Exception) {
            println("Error local al actualizar puntuación: ${e.message}")
        }
    }
}
