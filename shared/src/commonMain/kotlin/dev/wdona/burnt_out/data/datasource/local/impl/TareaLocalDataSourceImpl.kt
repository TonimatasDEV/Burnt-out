package dev.wdona.burnt_out.data.datasource.local.impl

import dev.wdona.burnt_out.data.dao.TareaDao
import dev.wdona.burnt_out.data.datasource.local.TareaLocalDataSource
import dev.wdona.burnt_out.shared.domain.Tarea

class TareaLocalDataSourceImpl(private val dao: TareaDao) : TareaLocalDataSource {

    override suspend fun getTareasByTablero(idTablero: Long): List<Tarea> =
        dao.getTareasByTablero(idTablero)

    override suspend fun getTareaById(
        idTarea: Long,
        idTablero: Long
    ): Tarea = dao.getTareaById(idTarea)

    override suspend fun crearTarea(tarea: Tarea) =
        dao.crearTarea(tarea)

    override suspend fun actualizarTarea(tarea: Tarea) =
        dao.actualizarTarea(tarea)

    override suspend fun eliminarTarea(tareaId: Long) =
        dao.eliminarTarea(tareaId)

    override suspend fun eliminarTareasPorTablero(idTablero: Long) {
        dao.eliminarTareasByTableroId(idTablero)
    }

}