package dev.wdona.burnt_out.data.repository

import dev.wdona.burnt_out.data.datasource.local.AjusteLocalDataSource
import dev.wdona.burnt_out.data.datasource.local.OperacionPendienteLocalDataSource
import dev.wdona.burnt_out.data.datasource.local.UsuarioLocalDataSource
import dev.wdona.burnt_out.data.datasource.remote.AjusteRemoteDataSource
import dev.wdona.burnt_out.domain.model.Ajuste
import dev.wdona.burnt_out.domain.repository.AjusteRepository

class AjusteRepositoryImpl(
    private val localDataSource: AjusteLocalDataSource,
    private val remoteDataSource: AjusteRemoteDataSource,
    private val pendienteDataSource: OperacionPendienteLocalDataSource
) : AjusteRepository {
    // FIXME
    override fun modificarAjuste(ajuste: Ajuste) {
        localDataSource.modificarAjuste(ajuste)
    }

    // FIXME
    override fun getAjustesByUsuario(idUsuario: Long): List<Ajuste> {
        return localDataSource.getAjustesByUsuario(idUsuario)
    }

    // FIXME
    override fun getAjusteByIdYUsuario(
        idAjuste: Long,
        idUsuario: Long
    ): Ajuste {
        return localDataSource.getAjusteByIdYUsuario(idAjuste, idUsuario)
    }

}