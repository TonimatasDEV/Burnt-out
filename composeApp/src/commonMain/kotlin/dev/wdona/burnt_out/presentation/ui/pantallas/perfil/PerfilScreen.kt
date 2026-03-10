package dev.wdona.burnt_out.presentation.ui.pantallas.perfil

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.presentation.ui.components.template.ScaffoldBase
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.MiPerfilViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.MiPerfilViewModel
import dev.wdona.burnt_out.shared.utils.SettingsManager
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.wdona.burnt_out.presentation.ui.components.ajustes.FilaAjusteInfo
import dev.wdona.burnt_out.presentation.ui.components.ajustes.FilaAjusteSwitch
import dev.wdona.burnt_out.presentation.ui.pantallas.SettingsContent
import dev.wdona.burnt_out.presentation.ui.pantallas.SettingsScreen
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.AjustesViewModelFactory

class PerfilScreen(val factory: MiPerfilViewModelFactory, val ajustesFactory: AjustesViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra

        val viewmodel = rememberScreenModel { factory.create() }

        val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

        PerfilContent(
            viewmodel,
            onAjustes = { navigator.push(SettingsScreen(ajustesFactory)) }
        )
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
fun PerfilContent(viewModel: MiPerfilViewModel, onAjustes: () -> Unit) {

    ScaffoldBase(
        titulo = "Perfil",
        onAjustes = onAjustes
    ) {

    }
}