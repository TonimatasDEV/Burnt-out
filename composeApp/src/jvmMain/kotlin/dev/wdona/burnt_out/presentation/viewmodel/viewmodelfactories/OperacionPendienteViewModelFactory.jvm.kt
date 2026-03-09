package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.data.dao.impl.OperacionPendienteDaoImpl
import dev.wdona.burnt_out.data.datasource.local.impl.OperacionPendienteLocalDataSourceImpl
import dev.wdona.burnt_out.data.repository.OperacionesPendientesRepositoryImpl
import dev.wdona.burnt_out.domain.repository.OperacionesPendientesRepository
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.OperacionesPendientesViewModel
import dev.wdona.burnt_out.shared.db.AppDatabase

actual class OperacionesPendientesViewModelFactory {
    actual fun create(): OperacionesPendientesViewModel {
        val driverFactory = DatabaseDriverFactory()
        val database = AppDatabase(driverFactory.createDriver())

        val pendienteDao = OperacionPendienteDaoImpl(database)
        val pendienteDataSource = OperacionPendienteLocalDataSourceImpl(pendienteDao)

        val repository = OperacionesPendientesRepositoryImpl(pendienteDataSource)

        return getInstance(repository)
    }

    companion object {
        private var instance: OperacionesPendientesViewModel? = null
        fun getInstance(repository: OperacionesPendientesRepository): OperacionesPendientesViewModel {
            if (instance == null) {
                instance = OperacionesPendientesViewModel(repository)
            }
            return instance!!
        }
    }
}
