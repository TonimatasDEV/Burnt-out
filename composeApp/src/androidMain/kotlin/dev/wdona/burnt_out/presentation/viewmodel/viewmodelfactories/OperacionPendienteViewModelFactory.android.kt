package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import android.content.Context
import dev.wdona.burnt_out.data.dao.impl.OperacionPendienteDaoImpl
import dev.wdona.burnt_out.data.datasource.local.impl.OperacionPendienteLocalDataSourceImpl
import dev.wdona.burnt_out.data.repository.OperacionesPendientesRepositoryImpl
import dev.wdona.burnt_out.domain.repository.OperacionesPendientesRepository
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.OperacionPendienteViewModel
import dev.wdona.burnt_out.shared.db.AppDatabase

actual class OperacionPendienteViewModelFactory(private val context: Context) {
    actual fun create(): OperacionPendienteViewModel {
        val driverFactory = DatabaseDriverFactory(context)
        val database = AppDatabase(driverFactory.createDriver())

        val pendienteDao = OperacionPendienteDaoImpl(database)
        val pendienteDataSource = OperacionPendienteLocalDataSourceImpl(pendienteDao)

        val repository = OperacionesPendientesRepositoryImpl(pendienteDataSource)

        return getInstance(repository)
    }

    companion object {
        private var instance: OperacionPendienteViewModel? = null
        fun getInstance(repository: OperacionesPendientesRepository): OperacionPendienteViewModel {
            if (instance == null) {
                instance = OperacionPendienteViewModel(repository)
            }
            return instance!!
        }
    }
}
