package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import android.content.Context
import dev.wdona.burnt_out.data.api.impl.UsuarioApiImpl
import dev.wdona.burnt_out.data.dao.impl.OperacionPendienteDaoImpl
import dev.wdona.burnt_out.data.dao.impl.UsuarioDaoImpl
import dev.wdona.burnt_out.data.datasource.local.impl.OperacionPendienteLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.local.impl.UsuarioLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.remote.impl.UsuarioRemoteDataSourceImpl
import dev.wdona.burnt_out.data.repository.UsuarioRepositoryImpl
import dev.wdona.burnt_out.domain.repository.UsuarioRepository
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.MiPerfilViewModel
import dev.wdona.burnt_out.shared.db.AppDatabase

actual class MiPerfilViewModelFactory(private val context: Context) {
    actual fun create(): MiPerfilViewModel {
        val driverFactory = DatabaseDriverFactory(context)
        val database = AppDatabase(driverFactory.createDriver())

        val dao = UsuarioDaoImpl(database)
        val api = UsuarioApiImpl()
        val pendienteDao = OperacionPendienteDaoImpl(database)

        val localDataSource = UsuarioLocalDataSourceImpl(dao)
        val remoteDataSource = UsuarioRemoteDataSourceImpl(api)
        val pendienteDataSource = OperacionPendienteLocalDataSourceImpl(pendienteDao)

        val repository = UsuarioRepositoryImpl(localDataSource, remoteDataSource, pendienteDataSource)

        return getInstance(repository)
    }

    companion object {
        private var instance: MiPerfilViewModel? = null
        fun getInstance(repository: UsuarioRepository): MiPerfilViewModel {
            if (instance == null) {
                instance = MiPerfilViewModel(repository)
            }
            return instance!!
        }
    }
}
