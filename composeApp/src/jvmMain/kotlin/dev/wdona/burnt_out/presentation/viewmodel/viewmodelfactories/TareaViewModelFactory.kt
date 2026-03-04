package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TareaViewModel

actual class TareaViewModelFactory {
    actual fun create(): TareaViewModel {
        return getInstance(DatabaseDriverFactory())
    }

    companion object {
        private var instance: TareaViewModel? = null
        fun getInstance(driverFactory: DatabaseDriverFactory): TareaViewModel {
            if (instance == null) {
                instance = TareaViewModel(driverFactory)
            }
            return instance!!
        }
    }
}