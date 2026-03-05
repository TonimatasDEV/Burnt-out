package dev.wdona.burnt_out.data.datasource.common

import dev.wdona.burnt_out.shared.domain.Tarea

interface TareaDataSource {
    suspend fun getTareasByTablero(idTablero: Long): List<Tarea>
    suspend fun getTareaById(idTarea: Long, idTablero: Long): Tarea
    suspend fun crearTarea(tarea: Tarea) : Boolean
    suspend fun actualizarTarea(tarea: Tarea) : Boolean
    suspend fun eliminarTarea(tareaId: Long) : Boolean
}