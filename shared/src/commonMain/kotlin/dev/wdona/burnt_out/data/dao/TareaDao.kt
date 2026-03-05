package dev.wdona.burnt_out.data.dao

import dev.wdona.burnt_out.shared.domain.Tarea

interface TareaDao {
    suspend fun getTareasByTablero(idTablero: Long): List<Tarea>
    suspend fun getTareaById(idTarea: Long): Tarea
    suspend fun crearTarea(tarea: Tarea) : Boolean
    suspend fun actualizarTarea(tarea: Tarea) : Boolean
    suspend fun eliminarTarea(tareaId: Long) : Boolean
    suspend fun insertOrUpdateTarea(tarea: Tarea): Boolean
    suspend fun eliminarTareasByTableroId(tableroId: Long)

}