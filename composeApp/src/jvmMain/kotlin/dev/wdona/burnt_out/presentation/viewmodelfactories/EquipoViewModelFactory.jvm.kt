package dev.wdona.burnt_out.presentation.viewmodelfactories

import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.presentation.viewmodels.EquipoViewModel

actual class EquipoViewModelFactory {
    actual fun create(): EquipoViewModel {
        return getInstance(DatabaseDriverFactory())
    }

    companion object {
        private var instance: EquipoViewModel? = null
        fun getInstance(driverFactory: DatabaseDriverFactory): EquipoViewModel {
            if (instance == null) {
                instance = EquipoViewModel(driverFactory)
            }
            return instance!!
        }
    }
}