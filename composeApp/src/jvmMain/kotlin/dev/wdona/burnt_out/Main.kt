package dev.wdona.burnt_out

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import burnt_out.composeapp.generated.resources.Res
import burnt_out.composeapp.generated.resources.logoBurntOutIcon
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.AjustesViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.MiEquipoViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.LeaderboardViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.MiPerfilViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.TablerosViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.TareasViewModelFactory
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.db.DatabaseInit

fun main() {
    val driver = DatabaseDriverFactory().createDriver()
    DatabaseInit.init(driver)

    application {
        Window(
            onCloseRequest = ::exitApplication,
//            alwaysOnTop = true,
            title = "Burn't out",
            icon = org.jetbrains.compose.resources.painterResource(Res.drawable.logoBurntOutIcon)
        ) {
            val tareasViewModelFactory = TareasViewModelFactory()
            val miEquipoViewModelFactory = MiEquipoViewModelFactory()
            val miPerfilViewModelFactory = MiPerfilViewModelFactory()
            val tablerosViewModelFactory = TablerosViewModelFactory()
            val leaderboardViewModelFactory = LeaderboardViewModelFactory()
            val ajustesViewModelFactory = AjustesViewModelFactory()


            App(
                tareasViewModelFactory,
                miEquipoViewModelFactory = miEquipoViewModelFactory,
                miPerfilViewModelFactory = miPerfilViewModelFactory,
                tablerosViewModelFactory = tablerosViewModelFactory,
                leaderboardViewModelFactory = leaderboardViewModelFactory,
                ajustesViewModelFactory = ajustesViewModelFactory
            )
        }
    }
}
