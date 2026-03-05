package dev.wdona.burnt_out.data.repository

import dev.wdona.burnt_out.data.datasource.local.EquipoLocalDataSource
import dev.wdona.burnt_out.data.datasource.local.OperacionPendienteLocalDataSource
import dev.wdona.burnt_out.data.datasource.mapper.EquipoMapper
import dev.wdona.burnt_out.data.datasource.remote.EquipoRemoteDataSource
import dev.wdona.burnt_out.domain.entity.Entity
import dev.wdona.burnt_out.domain.model.TipoAccion
import dev.wdona.burnt_out.domain.repository.EquipoRepository
import dev.wdona.burnt_out.shared.domain.Equipo
import dev.wdona.burnt_out.shared.domain.Usuario

class EquipoRepositoryImpl(
    private val local: EquipoLocalDataSource,
    private val remote: EquipoRemoteDataSource,
    private val pendiente: OperacionPendienteLocalDataSource
) : EquipoRepository {

    override suspend fun getEquiposByOrg(idOrg: Long): List<Equipo> {
        try {
            val equipos = remote.getEquiposByOrg(idOrg)
            local.eliminarEquiposPorOrg(idOrg)
            equipos.forEach { local.crearEquipo(it) }
        } catch (e: Exception) {
            println("Error al obtener equipos del servidor: ${e.message}")
        }
        return local.getEquiposByOrg(idOrg)
    }

    override suspend fun getEquipoById(idEquipo: Long): Equipo? {
        try {
            val equipo = remote.getEquipoById(idEquipo)
            local.eliminarEquipo(equipo.idEquipo)
            local.crearEquipo(equipo)
        } catch (e: Exception) {
            println("Error al obtener equipo del servidor: ${e.message}")
        }
        return local.getEquipoById(idEquipo)
    }

    override suspend fun crearEquipo(equipo: Equipo) {
        try {
            local.crearEquipo(equipo)
        } catch (e: Exception) {
            println("Error al crear equipo localmente: ${e.message}")
        }

        var exito = false
        try {
            exito = remote.crearEquipo(equipo)
        } catch (e: Exception) {
            println("Error al crear equipo en el servidor: ${e.message}")
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

    override suspend fun actualizarEquipo(equipo: Equipo) {
        try {
            local.actualizarEquipo(equipo)
        } catch (e: Exception) {
            println("Error al actualizar equipo localmente: ${e.message}")
        }

        var exito = false
        try {
            exito = remote.actualizarEquipo(equipo)
        } catch (e: Exception) {
            println("Error al actualizar equipo en el servidor: ${e.message}")
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

    override suspend fun eliminarEquipo(idEquipo: Long) {
        try {
            local.eliminarEquipo(idEquipo)
        } catch (e: Exception) {
            println("Error al eliminar equipo localmente: ${e.message}")
        }

        var exito = false
        try {
            exito = remote.eliminarEquipo(idEquipo)
        } catch (e: Exception) {
            println("Error al eliminar equipo en el servidor: ${e.message}")
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

    override suspend fun getMiembrosEquipo(idEquipo: Long): List<Usuario> {
        return try {
            remote.getMiembrosEquipo(idEquipo)
        } catch (e: Exception) {
            println("Error al obtener miembros del equipo: ${e.message}")
            emptyList()
        }
    }

    override suspend fun updatePuntuacion(idEquipo: Long, puntos: Long) {
        try {
            local.updatePuntuacion(idEquipo, puntos)
            // Aquí se podría llamar al remote también o registrar operación pendiente
        } catch (e: Exception) {
            println("Error al actualizar puntuación: ${e.message}")
        }
    }
}
