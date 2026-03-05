package dev.wdona.burnt_out.data.repository

import dev.wdona.burnt_out.data.dao.TableroRepository
import dev.wdona.burnt_out.data.datasource.local.OperacionPendienteLocalDataSource
import dev.wdona.burnt_out.data.datasource.local.TableroLocalDataSource
import dev.wdona.burnt_out.data.datasource.remote.TableroRemoteDataSource

class TableroRepositoryImpl(
    private val local: TableroLocalDataSource,
    private val remote: TableroRemoteDataSource,
    private val pendiente: OperacionPendienteLocalDataSource
) : TableroRepository {
}
