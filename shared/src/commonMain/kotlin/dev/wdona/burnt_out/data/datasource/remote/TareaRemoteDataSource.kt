package dev.wdona.burnt_out.data.datasource.remote

import dev.wdona.burnt_out.data.datasource.common.TareaDataSource
import dev.wdona.burnt_out.shared.domain.Tarea
import io.ktor.client.statement.HttpResponse

interface TareaRemoteDataSource : TareaDataSource {
    override suspend fun crearTarea(tarea: Tarea) ;
}