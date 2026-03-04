package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import android.content.Context
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.PerfilViewModel

actual class PerfilViewModelFactory(val context: Context) {
    actual fun create(): PerfilViewModel {
        return getInstance(DatabaseDriverFactory(context))
    }

    companion object {

        private var instance: PerfilViewModel? = null
        fun getInstance(driverFactory: DatabaseDriverFactory): PerfilViewModel {
            if (instance == null) {
                instance = PerfilViewModel(driverFactory)
            }
            return instance!!
        }
    }
}