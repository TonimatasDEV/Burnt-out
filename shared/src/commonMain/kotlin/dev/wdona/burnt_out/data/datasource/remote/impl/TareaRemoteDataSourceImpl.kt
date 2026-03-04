package dev.wdona.burnt_out.data.datasource.remote.impl

import dev.wdona.burnt_out.data.api.TareaApi
import dev.wdona.burnt_out.data.datasource.remote.TareaRemoteDataSource
import dev.wdona.burnt_out.shared.domain.Tarea

class TareaRemoteDataSourceImpl(private val api: TareaApi) : TareaRemoteDataSource {
    override suspend fun getTareasByTablero(idTablero: Long): List<Tarea> =
        api.getTareasByTablero(idTablero)

    override suspend fun getTareaById(
        idTarea: Long,
        idTablero: Long
    ): Tarea = api.getTareaById(idTarea, idTablero)

    override suspend fun crearTarea(tarea: Tarea) {
        api.crearTarea(tarea)
    }

    override suspend fun actualizarTarea(tarea: Tarea) {
        api.actualizarTarea(tarea)
    }

    override suspend fun eliminarTarea(tareaId: Long) {
        api.eliminarTarea(tareaId)
    }
}