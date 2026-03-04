package dev.wdona.burnt_out.data.repository

import dev.wdona.burnt_out.data.api.TareaApi
import dev.wdona.burnt_out.data.dao.TareaDao
import dev.wdona.burnt_out.data.dao.TareaRepository
import dev.wdona.burnt_out.shared.domain.Tarea

class TareaRepositoryImpl(private val dao: TareaDao, private val api: TareaApi) : TareaRepository {
    override suspend fun getTareasByTableroId(tableroId: Long): List<Tarea> {

    }

    override suspend fun getTareaById(idTarea: Long, idTablero: Long): Tarea? {

    }

    override suspend fun crearTarea(tarea: Tarea) {

    }

    override suspend fun actualizarTarea(tarea: Tarea) {

    }

    override suspend fun eliminarTarea(idTarea: Long) {
        TODO("Not yet implemented")
    }

}