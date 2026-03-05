package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import android.content.Context
import dev.wdona.burnt_out.data.api.impl.TareaApiImpl
import dev.wdona.burnt_out.data.dao.TareaRepository
import dev.wdona.burnt_out.data.dao.impl.OperacionPendienteDaoImpl
import dev.wdona.burnt_out.data.dao.impl.TareaDaoImpl
import dev.wdona.burnt_out.data.datasource.local.impl.OperacionPendienteLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.local.impl.TareaLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.remote.impl.TareaRemoteDataSourceImpl
import dev.wdona.burnt_out.data.repository.TareaRepositoryImpl
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TareaViewModel
import dev.wdona.burnt_out.shared.db.AppDatabase

actual class TareaViewModelFactory(private val context: Context) {

    actual fun create(): TareaViewModel {
        val driverFactory = DatabaseDriverFactory(context)
        val database = AppDatabase(driverFactory.createDriver())

        val dao = TareaDaoImpl(database)
        val api = TareaApiImpl()
        val pendienteDao = OperacionPendienteDaoImpl(database)

        val localDataSource = TareaLocalDataSourceImpl(dao)
        val remoteDataSource = TareaRemoteDataSourceImpl(api)
        val pendienteDataSource = OperacionPendienteLocalDataSourceImpl(pendienteDao)

        val repository = TareaRepositoryImpl(localDataSource, remoteDataSource, pendienteDataSource)

        return getInstance(repository)
    }

    companion object {
        private var instance: TareaViewModel? = null

        fun getInstance(repository: TareaRepository): TareaViewModel {
            if (instance == null) {
                instance = TareaViewModel(repository)
            }
            return instance!!
        }
    }
}