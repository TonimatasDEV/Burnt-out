package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import android.content.Context
import dev.wdona.burnt_out.data.api.impl.TableroApiImpl
import dev.wdona.burnt_out.data.dao.impl.OperacionPendienteDaoImpl
import dev.wdona.burnt_out.data.dao.impl.TableroDaoImpl
import dev.wdona.burnt_out.data.datasource.local.impl.OperacionPendienteLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.local.impl.TableroLocalDataSourceImpl
import dev.wdona.burnt_out.data.datasource.remote.impl.TableroRemoteDataSourceImpl
import dev.wdona.burnt_out.data.repository.TableroRepositoryImpl
import dev.wdona.burnt_out.domain.repository.TableroRepository
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TablerosViewModel
import dev.wdona.burnt_out.shared.db.AppDatabase
import java.io.Serializable
import kotlin.jvm.Transient

actual class TablerosViewModelFactory(@Transient private val context: Context) : Serializable {
    actual fun create(): TablerosViewModel {
        val driverFactory = DatabaseDriverFactory(context)
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
        private var instance: TablerosViewModel? = null

        fun getInstance(repository: TableroRepository): TablerosViewModel {
            if (instance == null) {
                instance = TablerosViewModel(repository)
            }
            return instance!!
        }
    }
}
