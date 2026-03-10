package dev.wdona.burnt_out.presentation.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.presentation.ui.components.common.BotonVolver
import dev.wdona.burnt_out.presentation.ui.components.template.ScaffoldBase
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.TareasViewModelFactory

// Pantalla de Ajustes
class SettingsScreen(val factory: TareasViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra


    }
}

@Composable
fun SettingsContent(onVolver: () -> Unit) {
    ScaffoldBase(titulo = "Ajustes") {
        Column {
            Text(text = "Ajustes")

        }
    }
}