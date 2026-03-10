package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.data.api.impl.EquipoApiImpl
import dev.wdona.burnt_out.data.api.impl.UsuarioApiImpl
import dev.wdona.burnt_out.data.dao.impl.EquipoDaoImpl
import dev.wdona.burnt_out.data.dao.impl.OperacionPendienteDaoImpl
import dev.wdona.burnt_out.data.dao.impl.UsuarioDaoImpl
import dev.wdona.burnt_out.data.datasource.local.impl.EquipoLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.local.impl.OperacionPendienteLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.local.impl.UsuarioLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.remote.impl.EquipoRemoteDataSourceImpl
import dev.wdona.burnt_out.data.datasource.remote.impl.UsuarioRemoteDataSourceImpl
import dev.wdona.burnt_out.data.repository.EquipoRepositoryImpl
import dev.wdona.burnt_out.data.repository.UsuarioRepositoryImpl
import dev.wdona.burnt_out.domain.repository.EquipoRepository
import dev.wdona.burnt_out.domain.repository.UsuarioRepository
import dev.wdona.burnt_out.domain.usecase.CargarMiembrosEquipo
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.EquipoViewModel
import dev.wdona.burnt_out.shared.db.DatabaseInit

actual class MiEquipoViewModelFactory {
    actual fun create(): EquipoViewModel {
        val database = DatabaseInit.getDatabase()

        val dao = EquipoDaoImpl(database)
        val api = EquipoApiImpl()
        val pendienteDao = OperacionPendienteDaoImpl(database)

        val localDataSource = EquipoLocalDataSourceImpl(dao)
        val remoteDataSource = EquipoRemoteDataSourceImpl(api)
        val pendienteDataSource = OperacionPendienteLocalDataSourceImpl(pendienteDao)

        val repository = EquipoRepositoryImpl(localDataSource, remoteDataSource, pendienteDataSource)

        val usuarioDao = UsuarioDaoImpl(database)
        val usuarioApi = UsuarioApiImpl()
        val usuarioLocalDataSource = UsuarioLocalDataSourceImpl(usuarioDao)
        val usuarioRemoteDataSource = UsuarioRemoteDataSourceImpl(usuarioApi)

        val usuarioRepository = UsuarioRepositoryImpl(
            usuarioLocalDataSource,
            remote = usuarioRemoteDataSource,
            pendiente = pendienteDataSource
        )

        return getInstance(repository, usuarioRepository)
    }

    companion object {
        private var instance: EquipoViewModel? = null
        fun getInstance(repository: EquipoRepository, usuarioRepository: UsuarioRepository): EquipoViewModel {
            if (instance == null) {
                instance = EquipoViewModel(repository, CargarMiembrosEquipo(usuarioRepository))
            }
            return instance!!
        }
    }
}
