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

    override suspend fun crearTarea(tarea: Tarea) {
        return queries.insertTarea(
            Titulo = tarea.titulo,
            Descripcion = tarea.descripcion,
            Estado = tarea.estado,
            FK_ID_Tabl = tarea.idTableroPerteneciente,
            FK_ID_Usuario = tarea.idUsuarioAsignado
        )
    }

    override suspend fun actualizarTarea(tarea: Tarea) {
        return queries.updateTareaById(
            ID_Tarea = tarea.idTarea,
            Titulo = tarea.titulo,
            Descripcion = tarea.descripcion,
            Estado = tarea.estado
        )
    }

    override suspend fun eliminarTarea(tareaId: Long){
        return queries.deleteTareaById(tareaId)
    }

    override suspend fun insertOrUpdateTarea(tarea: Tarea) {
        return queries.insertOrUpdateTarea(
            ID_Tarea = tarea.idTarea,
            Titulo = tarea.titulo,
            Descripcion = tarea.descripcion,
            Estado = tarea.estado,
            FK_ID_Tabl = tarea.idTableroPerteneciente,
            FK_ID_Usuario = tarea.idUsuarioAsignado
        )
    }

}