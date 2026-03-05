package dev.wdona.burnt_out.data.repository

import dev.wdona.burnt_out.data.dao.TareaRepository
import dev.wdona.burnt_out.data.datasource.local.OperacionPendienteLocalDataSource
import dev.wdona.burnt_out.data.datasource.local.TareaLocalDataSource
import dev.wdona.burnt_out.data.datasource.mapper.TareaMapper
import dev.wdona.burnt_out.data.datasource.remote.TareaRemoteDataSource
import dev.wdona.burnt_out.domain.entity.Entity
import dev.wdona.burnt_out.domain.model.TipoAccion
import dev.wdona.burnt_out.shared.domain.Tarea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TareaRepositoryImpl(
    private val local: TareaLocalDataSource,
    private val remote: TareaRemoteDataSource,
    private val pendiente: OperacionPendienteLocalDataSource
) : TareaRepository {

    private val repositoryScope = CoroutineScope(Dispatchers.Default)

    override suspend fun getTareasByTableroId(tableroId: Long): List<Tarea> {
        repositoryScope.launch {
            try {
                val tareas = remote.getTareasByTablero(tableroId)
                local.eliminarTareasPorTablero(tableroId)
                tareas.forEach { local.crearTarea(it) }
            } catch (e: Exception) {
                println("Servidor offline (getTareas): ${e.message}")
            }
        }
        return local.getTareasByTablero(tableroId)
    }

    override suspend fun getTareaById(idTarea: Long, idTablero: Long): Tarea? {
        repositoryScope.launch {
            try {
                val tarea = remote.getTareaById(idTarea, idTablero)
                local.eliminarTarea(tarea.idTarea)
                local.crearTarea(tarea)
            } catch (e: Exception) {
                println("Servidor offline (getTareaById): ${e.message}")
            }
        }
        return local.getTareaById(idTarea, idTablero)
    }

    override suspend fun crearTarea(tarea: Tarea) {
        var idLocal: Long = -1
        try {
            idLocal = local.crearTarea(tarea)
        } catch (e: Exception) {
            println("Error local al crear tarea: ${e.message}")
        }

        repositoryScope.launch {
            var exito = false
            var idRemoto: Long = -1
            try {
                idRemoto = remote.crearTarea(tarea) // FIXME
                exito = idRemoto != -1L
            } catch (e: Exception) {
                println("Servidor offline al crear tarea: ${e.message}")
            }

            try {
                val idParaPendiente = if (exito) idRemoto else idLocal
                
                pendiente.insertOperacionPendiente(
                    TipoAccion.CREACION.getNombreAccion(),
                    Entity.TAREA.getNombreEntity(),
                    idParaPendiente,
                    TareaMapper.toJson(tarea),
                    System.currentTimeMillis(),
                    if (exito) 1L else 0L
                )
            } catch (e: Exception) {
                println("Error al registrar operación pendiente: ${e.message}")
            }
        }
    }

    override suspend fun actualizarTarea(tarea: Tarea) {
        try {
            local.actualizarTarea(tarea)
        } catch (e: Exception) {
            println("Error local al actualizar tarea: ${e.message}")
        }

        repositoryScope.launch {
            var exito = false
            try {
                exito = remote.actualizarTarea(tarea)
            } catch (e: Exception) {
                println("Servidor offline al actualizar tarea: ${e.message}")
            }

            try {
                pendiente.insertOperacionPendiente(
                    TipoAccion.ACTUALIZACION.getNombreAccion(),
                    Entity.TAREA.getNombreEntity(),
                    tarea.idTarea,
                    TareaMapper.toJson(tarea),
                    System.currentTimeMillis(),
                    if (exito) 1L else 0L
                )
            } catch (e: Exception) {
                println("Error al registrar operación pendiente: ${e.message}")
            }
        }
    }

    override suspend fun eliminarTarea(idTarea: Long) {
        try {
            local.eliminarTarea(idTarea)
        } catch (e: Exception) {
            println("Error local al eliminar tarea: ${e.message}")
        }

        repositoryScope.launch {
            var exito = false
            try {
                exito = remote.eliminarTarea(idTarea)
            } catch (e: Exception) {
                println("Servidor offline al eliminar tarea: ${e.message}")
            }

            try {
                pendiente.insertOperacionPendiente(
                    TipoAccion.ELIMINACION.getNombreAccion(),
                    Entity.TAREA.getNombreEntity(),
                    idTarea,
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
