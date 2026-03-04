package dev.wdona.burnt_out.data.datasource.common

import dev.wdona.burnt_out.shared.domain.Tarea

interface TareaDataSource {
    suspend fun getTareasByTablero(idTablero: Long): List<Tarea>
    suspend fun getTareaById(idTarea: Long, idTablero: Long): Tarea
    suspend fun crearTarea(tarea: Tarea)
    suspend fun actualizarTarea(tarea: Tarea)
    suspend fun eliminarTarea(tareaId: Long)
}