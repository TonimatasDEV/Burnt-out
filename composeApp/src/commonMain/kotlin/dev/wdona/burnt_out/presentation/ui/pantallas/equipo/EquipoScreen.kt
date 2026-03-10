package dev.wdona.burnt_out.presentation.ui.pantallas.equipo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.MiEquipoViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.MiEquipoViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import dev.wdona.burnt_out.presentation.ui.components.equipo.MiembroCard
import dev.wdona.burnt_out.presentation.ui.components.template.ScaffoldBase

class EquipoScreen(val factory: MiEquipoViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra

        val viewModel = rememberScreenModel { factory.create() }
        val idEquipo = 1L // TODO: COGER ID DEL EQUIPO DEL USUARIO ACTIVO

        LaunchedEffect(idEquipo) {
            viewModel.cargarMiembrosEquipo(idEquipo)
        }

        EquipoContent(viewModel, onVolver = { navigator.pop() })

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EquipoContent(viewModel: MiEquipoViewModel, onVolver: () -> Unit) {
        val equipo by viewModel.uiStateEquipo.collectAsStateWithLifecycle()
        val miembros by viewModel.listaMiembros.collectAsStateWithLifecycle()

        ScaffoldBase(
            titulo = equipo?.titulo ?: "Mi equipo (off)",
        ) {
            Column {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 400.dp), // el min size es el tamanio ancho de cada tarjeta
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(miembros) { miembro ->
                        MiembroCard(miembro, onClick = {})
                    }
                }
            }
        }
    }

}
