package dev.wdona.burnt_out.data.repository

import dev.wdona.burnt_out.data.dao.TareaRepository
import dev.wdona.burnt_out.data.datasource.local.OperacionPendienteLocalDataSource
import dev.wdona.burnt_out.data.datasource.local.TareaLocalDataSource
import dev.wdona.burnt_out.data.datasource.mapper.TareaMapper
import dev.wdona.burnt_out.data.datasource.remote.TareaRemoteDataSource
import dev.wdona.burnt_out.domain.entity.Entity
import dev.wdona.burnt_out.domain.json.TareaJsonFields
import dev.wdona.burnt_out.domain.model.TipoAccion
import dev.wdona.burnt_out.shared.domain.Tarea

class TareaRepositoryImpl(private val local: TareaLocalDataSource, private val remote: TareaRemoteDataSource, private val pendiente: OperacionPendienteLocalDataSource) : TareaRepository {
    override suspend fun getTareasByTableroId(tableroId: Long): List<Tarea> {
        try {
            val tareas = remote.getTareasByTablero(tableroId)

            local.eliminarTareasPorTablero(tableroId)
            tareas.forEach { local.crearTarea(it) }
        } catch (e: Exception) {
            println("Error al obtener tareas del servidor: ${e.message}")
        }

        return local.getTareasByTablero(tableroId)
    }

    override suspend fun getTareaById(idTarea: Long, idTablero: Long): Tarea? {
        try {
            val tarea = remote.getTareaById(idTarea, idTablero)
            local.eliminarTarea(tarea.idTarea)
            local.crearTarea(tarea)
        } catch (e: Exception) {
            println("Error al obtener tarea del servidor: ${e.message}")
        }

        return local.getTareaById(idTarea, idTablero)
    }

    override suspend fun crearTarea(tarea: Tarea) {
        try {
            local.crearTarea(tarea)
        } catch (e: Exception) {
            println("Error al crear tarea localmente: ${e.message}")
        }

        var exito: Boolean = false
        try {
            exito = remote.crearTarea(tarea)
            if (!exito) {
                println("Error al crear tarea en el servidor: Respuesta no exitosa")
            }
        } catch (e: Exception) {
            println("Error al crear tarea en el servidor: ${e.message}")
        }

        try {
            pendiente.insertOperacionPendiente(TipoAccion.CREACION.getNombreAccion(), Entity.TAREA.getNombreEntity(), tarea.idTarea,
                TareaMapper.toJson(tarea), System.currentTimeMillis(), if (exito) 1L else 0L)
        } catch (e: Exception) {
            println("Error al registrar operación pendiente: ${e.message}")
        }
    }

    override suspend fun actualizarTarea(tarea: Tarea) {
        try {
            local.actualizarTarea(tarea)
        } catch (e: Exception) {
            println("Error al actualizar tarea localmente: ${e.message}")
        }

        var exito: Boolean = false
        try {
            exito = remote.actualizarTarea(tarea)
            if (!exito) {
                println("Error al actualizar tarea en el servidor: Respuesta no exitosa")
            }
        } catch (e: Exception) {
            println("Error al actualizar tarea en el servidor: ${e.message}")
        }

        try {
            pendiente.insertOperacionPendiente(TipoAccion.ACTUALIZACION.getNombreAccion(), Entity.TAREA.getNombreEntity(), tarea.idTarea,
                TareaMapper.toJson(tarea), System.currentTimeMillis(), if (exito) 1L else 0L)
        } catch (e: Exception) {
            println("Error al registrar operación pendiente: ${e.message}")
        }

    }

    override suspend fun eliminarTarea(idTarea: Long) {
        try {
            local.eliminarTarea(idTarea)
        } catch (e: Exception) {
            println("Error al eliminar tarea localmente: ${e.message}")
        }

        var exito: Boolean = false
        try {
            exito = remote.eliminarTarea(idTarea)
            if (!exito) {
                println("Error al eliminar tarea en el servidor: Respuesta no exitosa")
            }
        } catch (e: Exception) {
            println("Error al eliminar tarea en el servidor: ${e.message}")
        }

        try {
            pendiente.insertOperacionPendiente(TipoAccion.ELIMINACION.getNombreAccion(), Entity.TAREA.getNombreEntity(), idTarea,
                "", System.currentTimeMillis(), if (exito) 1L else 0L)
        } catch (e: Exception) {
            println("Error al registrar operación pendiente: ${e.message}")
        }
    }

}