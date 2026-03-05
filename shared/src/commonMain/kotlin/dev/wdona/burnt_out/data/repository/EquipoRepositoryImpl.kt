package dev.wdona.burnt_out.data.repository

import dev.wdona.burnt_out.data.dao.EquipoRepository
import dev.wdona.burnt_out.data.datasource.local.EquipoLocalDataSource
import dev.wdona.burnt_out.data.datasource.local.OperacionPendienteLocalDataSource
import dev.wdona.burnt_out.data.datasource.remote.EquipoRemoteDataSource

class EquipoRepositoryImpl(
    private val local: EquipoLocalDataSource,
    private val remote: EquipoRemoteDataSource,
    private val pendiente: OperacionPendienteLocalDataSource
) : EquipoRepository {
}
