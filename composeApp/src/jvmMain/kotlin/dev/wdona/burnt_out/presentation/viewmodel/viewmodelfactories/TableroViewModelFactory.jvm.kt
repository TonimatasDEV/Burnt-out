package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TableroViewModel

actual class TableroViewModelFactory {
    actual fun create(): TableroViewModel {
        return getInstance(DatabaseDriverFactory())
    }

    companion object {
        private var instance: TableroViewModel? = null
        fun getInstance(driverFactory: DatabaseDriverFactory): TableroViewModel {
            if (instance == null) {
                instance = TableroViewModel(driverFactory)
            }
            return instance!!
        }
    }
}