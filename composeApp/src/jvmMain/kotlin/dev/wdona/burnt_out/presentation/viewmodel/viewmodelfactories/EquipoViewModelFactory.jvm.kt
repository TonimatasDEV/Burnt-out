package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.data.api.impl.EquipoApiImpl
import dev.wdona.burnt_out.data.dao.impl.EquipoDaoImpl
import dev.wdona.burnt_out.data.dao.impl.OperacionPendienteDaoImpl
import dev.wdona.burnt_out.data.dao.impl.UsuarioDaoImpl
import dev.wdona.burnt_out.data.datasource.local.impl.EquipoLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.local.impl.OperacionPendienteLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.local.impl.UsuarioLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.remote.impl.EquipoRemoteDataSourceImpl
import dev.wdona.burnt_out.data.repository.EquipoRepositoryImpl
import dev.wdona.burnt_out.domain.repository.EquipoRepository
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.EquipoViewModel
import dev.wdona.burnt_out.shared.db.AppDatabase

actual class EquipoViewModelFactory {
    actual fun create(): EquipoViewModel {
        val driverFactory = DatabaseDriverFactory()
        val database = AppDatabase(driverFactory.createDriver())

        val dao = EquipoDaoImpl(database)
        val api = EquipoApiImpl()
        val pendienteDao = OperacionPendienteDaoImpl(database)
        val usuarioDao = UsuarioDaoImpl(database)

        val localDataSource = EquipoLocalDataSourceImpl(dao)
        val remoteDataSource = EquipoRemoteDataSourceImpl(api)
        val pendienteDataSource = OperacionPendienteLocalDataSourceImpl(pendienteDao)
        val usuarioLocalDataSource = UsuarioLocalDataSourceImpl(usuarioDao)

        val repository = EquipoRepositoryImpl(localDataSource, remoteDataSource, pendienteDataSource, usuarioLocalDataSource)

        return getInstance(repository)
    }

    companion object {
        private var instance: EquipoViewModel? = null
        fun getInstance(repository: EquipoRepository): EquipoViewModel {
            if (instance == null) {
                instance = EquipoViewModel(repository)
            }
            return instance!!
        }
    }
}
