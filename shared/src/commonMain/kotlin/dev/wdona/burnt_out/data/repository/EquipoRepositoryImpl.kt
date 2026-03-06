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
                val equipos = remote.getEquiposByOrg(idOrg)
                // Sincronización atómica: Insert or Replace gestionado por el DAO
                equipos.forEach { local.insertEquipo(it) }
            } catch (e: Exception) {
                println("Servidor offline (getEquiposByOrg): ${e.message}")
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
                val equipo = remote.getEquipoById(idEquipo)
                local.insertEquipo(equipo)
            } catch (e: Exception) {
                println("Servidor offline (getEquipoById): ${e.message}")
            }
        }
        try {
            local.getEquipoById(idEquipo)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun crearEquipo(equipo: Equipo) {
        withContext(Dispatchers.IO) {
            try {
                local.crearEquipo(equipo)
            } catch (e: Exception) {
                println("Error local al crear equipo: ${e.message}")
            }
        }

        repositoryScope.launch {
            var exito = false
            try {
                exito = remote.crearEquipo(equipo)
            } catch (e: Exception) {
                println("Servidor offline al crear equipo: ${e.message}")
            }

            withContext(Dispatchers.IO) {
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
    }

    override suspend fun actualizarEquipo(equipo: Equipo) {
        withContext(Dispatchers.IO) {
            try {
                local.actualizarEquipo(equipo)
            } catch (e: Exception) {
                println("Error local al actualizar equipo: ${e.message}")
            }
        }

        repositoryScope.launch {
            var exito = false
            try {
                exito = remote.actualizarEquipo(equipo)
            } catch (e: Exception) {
                println("Servidor offline al actualizar equipo: ${e.message}")
            }

            withContext(Dispatchers.IO) {
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
    }

    override suspend fun eliminarEquipo(idEquipo: Long) {
        withContext(Dispatchers.IO) {
            try {
                local.eliminarEquipo(idEquipo)
            } catch (e: Exception) {
                println("Error local al eliminar equipo: ${e.message}")
            }
        }

        repositoryScope.launch {
            var exito = false
            try {
                exito = remote.eliminarEquipo(idEquipo)
            } catch (e: Exception) {
                println("Servidor offline al eliminar equipo: ${e.message}")
            }

            withContext(Dispatchers.IO) {
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
    }

    override suspend fun getMiembrosEquipo(idEquipo: Long): List<Usuario> = withContext(Dispatchers.IO) {
        repositoryScope.launch {
            try {
                remote.getMiembrosEquipo(idEquipo)
            } catch (e: Exception) {
                println("Servidor offline (getMiembrosEquipo): ${e.message}")
            }
        }
        try {
            usuarioLocal.getUsuariosByEquipo(idEquipo)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun updatePuntuacion(idEquipo: Long, puntos: Long) {
        withContext(Dispatchers.IO) {
            try {
                local.updatePuntuacion(idEquipo, puntos)
            } catch (e: Exception) {
                println("Error local al actualizar puntuación: ${e.message}")
            }
        }
    }
}
