package dev.wdona.burnt_out.presentation.ui.pantallas.perfil

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.presentation.ui.components.template.ScaffoldBase
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.MiPerfilViewModelFactory

class PerfilScreen(val factory: MiPerfilViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra

        PerfilContent(onVolver = { navigator.pop() })
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PerfilContent(onVolver: () -> Unit) {
    ScaffoldBase(
        titulo = "Perfil",
    ) {
        Column {

        }

    }
}