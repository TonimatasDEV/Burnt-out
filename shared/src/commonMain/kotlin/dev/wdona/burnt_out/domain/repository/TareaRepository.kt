package dev.wdona.burnt_out.data.dao

import dev.wdona.burnt_out.shared.domain.Tarea

interface TareaRepository {
    suspend fun getTareasByTableroId(tableroId: Long): List<Tarea>
    suspend fun getTareaById(idTarea: Long, idTablero: Long): Tarea?
    suspend fun crearTarea(tarea: Tarea)
    suspend fun actualizarTarea(tarea: Tarea)
    suspend fun eliminarTarea(idTarea: Long)
}