package dev.wdona.burnt_out.data.datasource.remote.impl

import dev.wdona.burnt_out.data.api.TareaApi
import dev.wdona.burnt_out.data.datasource.remote.TareaRemoteDataSource
import dev.wdona.burnt_out.shared.domain.Tarea
import io.ktor.client.statement.HttpResponse

class TareaRemoteDataSourceImpl(private val api: TareaApi) : TareaRemoteDataSource {
    override suspend fun getTareasByTablero(idTablero: Long): List<Tarea> =
        api.getTareasByTablero(idTablero)

    override suspend fun getTareaById(
        idTarea: Long,
        idTablero: Long
    ): Tarea = api.getTareaById(idTarea, idTablero)

    override suspend fun crearTarea(tarea: Tarea): Boolean {
        val response = api.crearTarea(tarea)
        return response.status.value in 200..299 // FIXME
    }

    override suspend fun actualizarTarea(tarea: Tarea): Boolean {
        val response = api.actualizarTarea(tarea)
        return response.status.value in 200..299 // FIXME
    }

    override suspend fun eliminarTarea(tareaId: Long): Boolean {
        val response = api.eliminarTarea(tareaId)
        return response.status.value in 200..299 // FIXME
    }
}