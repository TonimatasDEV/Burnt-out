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
import kotlinx.coroutines.withContext

class EquipoRepositoryImpl(
    private val local: EquipoLocalDataSource,
    private val remote: EquipoRemoteDataSource,
    private val pendiente: OperacionPendienteLocalDataSource,
    private val usuarioLocal: UsuarioLocalDataSource
) : EquipoRepository {

    private val repositoryScope = CoroutineScope(Dispatchers.Default)

    override suspend fun getEquiposByOrg(idOrg: Long): List<Equipo> = withContext(Dispatchers.IO) {
        repositoryScope.launch {
            try {
                val equiposRemotos = remote.getEquiposByOrg(idOrg)
                if (equiposRemotos.isNotEmpty()) {
                    equiposRemotos.forEach { local.insertOrUpdateEquipo(it) }
                }
            } catch (e: Exception) {
                println("Sincronización de equipos omitida (servidor offline)")
            }
        }
        try {
            local.getEquiposByOrg(idOrg)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getEquipoById(idEquipo: Long): Equipo? = withContext(Dispatchers.IO) {
        repositoryScope.launch {
            try {
                val equipoRemoto = remote.getEquipoById(idEquipo)
                local.insertOrUpdateEquipo(equipoRemoto)
            } catch (e: Exception) {
                println("Sincronización de equipo individual omitida")
            }
        }
        try {
            local.getEquipoById(idEquipo)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun crearEquipo(equipo: Equipo) {
        val idGenerado = withContext(Dispatchers.IO) {
            try {
                local.crearEquipo(equipo)
            } catch (e: Exception) {
                -1L
            }
        }

        repositoryScope.launch {
            var exito = false
            try {
                exito = remote.crearEquipo(equipo)
            } catch (e: Exception) {
                // Servidor offline
            }

            withContext(Dispatchers.IO) {
                pendiente.insertOperacionPendiente(
                    TipoAccion.CREACION.getNombreAccion(),
                    Entity.EQUIPO.getNombreEntity(),
                    if (exito) equipo.idEquipo else idGenerado,
                    EquipoMapper.toJson(equipo),
                    System.currentTimeMillis(),
                    if (exito) 1L else 0L
                )
            }
        }
    }

    override suspend fun actualizarEquipo(equipo: Equipo) {
        withContext(Dispatchers.IO) {
            local.actualizarEquipo(equipo)
        }

        repositoryScope.launch {
            var exito = false
            try {
                exito = remote.actualizarEquipo(equipo)
            } catch (e: Exception) {}

            withContext(Dispatchers.IO) {
                pendiente.insertOperacionPendiente(
                    TipoAccion.ACTUALIZACION.getNombreAccion(),
                    Entity.EQUIPO.getNombreEntity(),
                    equipo.idEquipo,
                    EquipoMapper.toJson(equipo),
                    System.currentTimeMillis(),
                    if (exito) 1L else 0L
                )
            }
        }
    }

    override suspend fun eliminarEquipo(idEquipo: Long) {
        withContext(Dispatchers.IO) {
            local.eliminarEquipo(idEquipo)
        }

        repositoryScope.launch {
            var exito = false
            try {
                exito = remote.eliminarEquipo(idEquipo)
            } catch (e: Exception) {}

            withContext(Dispatchers.IO) {
                pendiente.insertOperacionPendiente(
                    TipoAccion.ELIMINACION.getNombreAccion(),
                    Entity.EQUIPO.getNombreEntity(),
                    idEquipo,
                    "",
                    System.currentTimeMillis(),
                    if (exito) 1L else 0L
                )
            }
        }
    }

    override suspend fun getMiembrosEquipo(idEquipo: Long): List<Usuario> = withContext(Dispatchers.IO) {
        repositoryScope.launch {
            try {
                remote.getMiembrosEquipo(idEquipo)
            } catch (e: Exception) {}
        }
        try {
            usuarioLocal.getUsuariosByEquipo(idEquipo)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun updatePuntuacion(idEquipo: Long, puntos: Long) {
        withContext(Dispatchers.IO) {
            local.updatePuntuacion(idEquipo, puntos)
        }
    }
}
