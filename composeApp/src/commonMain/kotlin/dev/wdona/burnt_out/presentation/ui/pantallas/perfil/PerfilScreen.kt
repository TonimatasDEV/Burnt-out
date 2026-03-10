package dev.wdona.burnt_out.presentation.ui.pantallas.perfil

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.presentation.ui.components.template.ScaffoldBase
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.MiPerfilViewModelFactory
import dev.wdona.burnt_out.shared.utils.SettingsManager

class PerfilScreen(val factory: MiPerfilViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra

        PerfilContent(onVolver = { navigator.pop() })
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
fun PerfilContent(onVolver: () -> Unit) {
    ScaffoldBase(
        titulo = "Perfil",
    ) {
        Column {
            Text(text = "Ajustes")
            OutlinedButton(onClick = { SettingsManager().setPrimeraEjecucion(!SettingsManager().getPrimeraEjecucion()) }) {
                if (SettingsManager().getPrimeraEjecucion()) {
                    Text(text = "No es primera ejecucion")
                } else {
                    Text(text = "Es primera ejecucion")
                }
            }
            Text(SettingsManager().getPrimeraEjecucion().toString())

            Text(SettingsManager().getTokenUsuario().toString())

            Text(SettingsManager().getIdUsuarioActual().toString())

        }
    }
}