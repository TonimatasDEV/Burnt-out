package dev.wdona.burnt_out.data.api.impl

import dev.wdona.burnt_out.data.api.TareaApi
import dev.wdona.burnt_out.shared.domain.Tarea
import dev.wdona.burnt_out.shared.network.ApiClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse

class TareaApiImpl(private val client: HttpClient = ApiClient.client) : TareaApi {
    override suspend fun getTareasByTablero(idTablero: Long): List<Tarea> = client.get("$idTablero/").body()

    override suspend fun getTareaById(idTarea: Long, idTablero: Long): Tarea = client.get("$idTablero/$idTarea").body()

    override suspend fun crearTarea(tarea: Tarea) : HttpResponse =
        client.post(
            "/nuevo/tarea/tarea=" +
                    "${tarea.titulo}" +
                    "&descripcion=${tarea.descripcion}" +
                    "&estado=${tarea.estado}" +
                    "&idTableroPerteneciente=${tarea.idTableroPerteneciente}" +
                    "&idUsuarioAsignado=${tarea.idUsuarioAsignado}"
        )

    override suspend fun actualizarTarea(tarea: Tarea) {
        client.post(
            "/actualizar/tarea/idTarea=${tarea.idTarea}" +
                    "&titulo=${tarea.titulo}" +
                    "&descripcion=${tarea.descripcion}" +
                    "&estado=${tarea.estado}"
        )
    }

    override suspend fun eliminarTarea(idTarea: Long) {
        client.post("/eliminar/tarea/idTarea=$idTarea")
    }

}