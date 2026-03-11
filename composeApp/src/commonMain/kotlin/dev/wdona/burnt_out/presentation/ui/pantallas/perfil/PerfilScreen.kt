package dev.wdona.burnt_out.presentation.ui.pantallas.perfil

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.presentation.ui.components.template.ScaffoldBase
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.MiPerfilViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.PerfilViewModel
import dev.wdona.burnt_out.shared.utils.SettingsManager
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.wdona.burnt_out.presentation.ui.pantallas.SettingsScreen
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.AjustesViewModelFactory

class PerfilScreen(val factory: MiPerfilViewModelFactory, val ajustesFactory: AjustesViewModelFactory, val onVolver: (() -> Unit)? = null) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra

        val viewmodel = rememberScreenModel { factory.create() }
        val idUsuario = SettingsManager.getIdUsuarioActual()

        LaunchedEffect(idUsuario) {
            viewmodel.cargarUsuario(idUsuario)
        }

        PerfilContent(
            viewmodel,
            onAjustes = { navigator.push(SettingsScreen(ajustesFactory)) },
            onVolver = onVolver
        )
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
fun PerfilContent(viewModel: PerfilViewModel, onAjustes: () -> Unit, onVolver: (() -> Unit)? = null) {
    val usuario by viewModel.uiState.collectAsStateWithLifecycle()

    ScaffoldBase(
        titulo = usuario?.nombre ?: SettingsManager.getNombreUsuario(),
        onAjustes = onAjustes,
        onVolver = onVolver
    ) {
        if (usuario == null) {
            Text(
                text = "No se ha podido cargar el usuario",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            Column {
                Text("ID: ${usuario!!.idUsuario}", style = MaterialTheme.typography.titleMedium)
                Text(usuario!!.username, style = MaterialTheme.typography.titleMedium)
                Text(usuario!!.descripcion?: "Sin descripcion", style = MaterialTheme.typography.titleMedium)
                Text(if ((usuario!!.riesgoBurnout ?: 0.0) > 33.0) "Riesgo de Burn out" else "No hay riesgo")
            }
        }
    }
}