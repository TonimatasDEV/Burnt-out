package dev.wdona.burnt_out

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import burnt_out.composeapp.generated.resources.Res
import burnt_out.composeapp.generated.resources.logoBurntOutIcon
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.EquipoViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.PerfilViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.TableroViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.TareaViewModelFactory
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.db.DatabaseInit

fun main() {
    val driver = DatabaseDriverFactory().createDriver()
    DatabaseInit.init(driver)

    application {
        Window(
            onCloseRequest = ::exitApplication,
            alwaysOnTop = true,
            title = "Burnt out",
            icon = org.jetbrains.compose.resources.painterResource(Res.drawable.logoBurntOutIcon)
        ) {
            val tareaViewModelFactory = TareaViewModelFactory()
            val equipoViewModelFactory = EquipoViewModelFactory()
            val perfilViewModelFactory = PerfilViewModelFactory()
            val tableroViewModelFactory = TableroViewModelFactory()
            App(
                tareaViewModelFactory,
                equipoViewModelFactory = equipoViewModelFactory,
                perfilViewModelFactory = perfilViewModelFactory,
                tableroViewModelFactory = tableroViewModelFactory
            )
        }
    }
}
