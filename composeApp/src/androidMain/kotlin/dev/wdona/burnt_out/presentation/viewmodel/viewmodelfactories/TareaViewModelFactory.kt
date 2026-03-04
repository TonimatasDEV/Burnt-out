package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import android.content.Context
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TareaViewModel

actual class TareaViewModelFactory(private val context: Context) {

    actual fun create(): TareaViewModel {
        return getInstance(DatabaseDriverFactory(context))
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