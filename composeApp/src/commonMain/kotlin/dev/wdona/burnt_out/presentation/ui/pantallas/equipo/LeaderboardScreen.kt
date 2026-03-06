package dev.wdona.burnt_out.presentation.ui.pantallas.equipo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.presentation.ui.components.common.InfoTopBarCustomTitle
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.EquipoViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.LeaderboardViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.EquipoViewModel
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.LeaderboardViewModel

class LeaderboardScreen(val factory: LeaderboardViewModelFactory, val idOrg: Long) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra

        val viewModel = rememberScreenModel { factory.create() }

        LaunchedEffect(idOrg) {
            viewModel.cargarLeaderboard(idOrg)
        }

        LeaderboardContent(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardContent(leaderboardViewModel: LeaderboardViewModel) {
    val listaEquipos by leaderboardViewModel.leaderboard.collectAsState()
    
    Scaffold(
        topBar = { InfoTopBarCustomTitle("Leaderboard") }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxWidth()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                items(listaEquipos, key = { it.idEquipo }) { equipo ->
                    CardEquipo(
                        nombreEquipo = equipo.titulo,
                        puntosEquipo = equipo.puntuacion
                    )
                }
            }
        }
    }
}

@Composable
fun CardEquipo(nombreEquipo: String, puntosEquipo: Long?) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = nombreEquipo, style = androidx.compose.material3.MaterialTheme.typography.titleMedium)
        Text(text = "Puntos: ${puntosEquipo ?: 0}", style = androidx.compose.material3.MaterialTheme.typography.bodyMedium)
    }
}
