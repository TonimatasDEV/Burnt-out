package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.data.api.impl.TableroApiImpl
import dev.wdona.burnt_out.data.dao.TableroRepository
import dev.wdona.burnt_out.data.dao.impl.OperacionPendienteDaoImpl
import dev.wdona.burnt_out.data.dao.impl.TableroDaoImpl
import dev.wdona.burnt_out.data.datasource.local.impl.OperacionPendienteLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.local.impl.TableroLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.remote.impl.TableroRemoteDataSourceImpl
import dev.wdona.burnt_out.data.repository.TableroRepositoryImpl
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TableroViewModel
import dev.wdona.burnt_out.shared.db.AppDatabase

actual class TableroViewModelFactory {
    actual fun create(): TableroViewModel {
        val driverFactory = DatabaseDriverFactory()
        val database = AppDatabase(driverFactory.createDriver())

        val dao = TableroDaoImpl(database)
        val api = TableroApiImpl()
        val pendienteDao = OperacionPendienteDaoImpl(database)

        val localDataSource = TableroLocalDataSourceImpl(dao)
        val remoteDataSource = TableroRemoteDataSourceImpl(api)
        val pendienteDataSource = OperacionPendienteLocalDataSourceImpl(pendienteDao)

        val repository = TableroRepositoryImpl(localDataSource, remoteDataSource, pendienteDataSource)

        return getInstance(repository)
    }

    companion object {
        private var instance: TableroViewModel? = null
        fun getInstance(repository: TableroRepository): TableroViewModel {
            if (instance == null) {
                instance = TableroViewModel(repository)
            }
            return instance!!
        }
    }
}
