package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import android.content.Context
import dev.wdona.burnt_out.data.api.impl.AjusteApiImpl
import dev.wdona.burnt_out.data.dao.impl.AjusteDaoImpl
import dev.wdona.burnt_out.data.dao.impl.OperacionPendienteDaoImpl
import dev.wdona.burnt_out.data.dao.impl.UsuarioDaoImpl
import dev.wdona.burnt_out.data.datasource.local.impl.AjusteLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.local.impl.OperacionPendienteLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.local.impl.UsuarioLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.remote.impl.AjusteRemoteDataSourceImpl
import dev.wdona.burnt_out.data.repository.AjusteRepositoryImpl
import dev.wdona.burnt_out.domain.repository.AjusteRepository
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.AjustesViewModel
import dev.wdona.burnt_out.shared.db.AppDatabase
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import java.io.Serializable
import kotlin.jvm.Transient
actual class AjustesViewModelFactory(@Transient private val context: Context) : Serializable {
    actual fun create(): AjustesViewModel {
        val driverFactory = DatabaseDriverFactory(context)
        val database = AppDatabase(driverFactory.createDriver())

        val dao = AjusteDaoImpl(database)
        val api = AjusteApiImpl()
        val pendienteDao = OperacionPendienteDaoImpl(database)

        val localDataSource = AjusteLocalDataSourceImpl(dao)
        val remoteDataSource = AjusteRemoteDataSourceImpl(api)
        val pendienteDataSource = OperacionPendienteLocalDataSourceImpl(pendienteDao)

        val repository = AjusteRepositoryImpl(
            localDataSource,
            remoteDataSource,
            pendienteDataSource,
        )

        return getInstance(repository)
    }

    companion object {
        private var instance: AjustesViewModel? = null

        fun getInstance(repository: AjusteRepository): AjustesViewModel {
            if (instance == null) {
                instance = AjustesViewModel(repository)
            }
            return instance!!
        }
    }
}