package dev.wdona.burnt_out.presentation.ui.pantallas.tablero

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import dev.wdona.burnt_out.presentation.ui.components.tablero.CardTablero
import dev.wdona.burnt_out.presentation.ui.components.template.ScaffoldBase
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.TableroViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.TareaViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TableroViewModel

class TablerosScreen(
    private val tableroFactory: TableroViewModelFactory,
    private val tareaViewModelFactory: TareaViewModelFactory
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val tableroViewModel: TableroViewModel = rememberScreenModel { tableroFactory.create() }
        val idOrg = 1L

        LaunchedEffect(idOrg) {
            tableroViewModel.cargarTableros(idOrg)
        }

        MenuTableros(
            tableroViewModel = tableroViewModel,
            onIrACrearTablero = { 
                navigator.push(MenuCrearTableroScreen(tableroFactory)) 
            },
            onVerTablero = { idTablero, nombreTablero ->
                navigator.push(
                    DetalleTableroScreen(
                        idTablero = idTablero,
                        nombreTablero = nombreTablero,
                        tareaViewModelFactory = tareaViewModelFactory
                    )
                )
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MenuTableros(
        tableroViewModel: TableroViewModel,
        onIrACrearTablero: () -> Unit,
        onVerTablero: (Long, String) -> Unit
    ) {
        val listaTableros by tableroViewModel.listaTableros.collectAsState()

        ScaffoldBase(
            titulo = "Tableros",
            onCrear = onIrACrearTablero,
            textoFABCrear = "Nuevo Tablero"
        ) {
            if (listaTableros.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(
                        "No hay tableros aun",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    items(listaTableros) { tablero ->
                        CardTablero(
                            tituloTablero = tablero.titulo,
                            onClick = { onVerTablero(tablero.idTablero, tablero.titulo) }
                        )
                    }
                }
            }
        }
    }
}
