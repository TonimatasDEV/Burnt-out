package dev.wdona.burnt_out.data.dao.impl

import dev.wdona.burnt_out.data.dao.TareaDao
import dev.wdona.burnt_out.data.datasource.mapper.TareaMapper
import dev.wdona.burnt_out.shared.db.AppDatabase
import dev.wdona.burnt_out.shared.domain.Tarea

class TareaDaoImpl(appDatabase: AppDatabase) : TareaDao {
    private val queries = appDatabase.appDatabaseQueries

    override suspend fun getTareasByTablero(idTablero: Long): List<Tarea> {
        return TareaMapper.toDomainList(
            queries.getTareasByTablero(idTablero)
        )
    }

    override suspend fun getTareaById(idTarea: Long): Tarea {
        return TareaMapper.toDomain(
            queries.getTareaById(idTarea)
        )
    }

    override suspend fun crearTarea(tarea: Tarea) : Boolean {
        try {
            queries.insertTarea(
                Titulo = tarea.titulo,
                Descripcion = tarea.descripcion,
                Estado = tarea.estado,
                FK_ID_Tabl = tarea.idTableroPerteneciente,
                FK_ID_Usuario = tarea.idUsuarioAsignado
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override suspend fun actualizarTarea(tarea: Tarea) : Boolean {
        try {
            queries.updateTareaById(
                ID_Tarea = tarea.idTarea,
                Titulo = tarea.titulo,
                Descripcion = tarea.descripcion,
                Estado = tarea.estado
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        return true
    }

    override suspend fun eliminarTarea(tareaId: Long) : Boolean {
        try {
            queries.deleteTareaById(tareaId)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        return true
    }

    override suspend fun insertOrUpdateTarea(tarea: Tarea) : Boolean {
        try {
            queries.insertOrUpdateTarea(
                ID_Tarea = tarea.idTarea,
                Titulo = tarea.titulo,
                Descripcion = tarea.descripcion,
                Estado = tarea.estado,
                FK_ID_Tabl = tarea.idTableroPerteneciente,
                FK_ID_Usuario = tarea.idUsuarioAsignado
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        return true
    }

    override suspend fun eliminarTareasByTableroId(tableroId: Long) {
        try {
            queries.deleteTareasByTablero(tableroId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}