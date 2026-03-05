package dev.wdona.burnt_out.data.repository

import dev.wdona.burnt_out.data.dao.UsuarioRepository
import dev.wdona.burnt_out.data.datasource.local.OperacionPendienteLocalDataSource
import dev.wdona.burnt_out.data.datasource.local.UsuarioLocalDataSource
import dev.wdona.burnt_out.data.datasource.remote.UsuarioRemoteDataSource

class UsuarioRepositoryImpl(
    private val local: UsuarioLocalDataSource,
    private val remote: UsuarioRemoteDataSource,
    private val pendiente: OperacionPendienteLocalDataSource
) : UsuarioRepository {
}
