package dev.wdona.burnt_out.presentation.ui.pantallas.tablero

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.presentation.ui.components.tablero.CardTablero
import dev.wdona.burnt_out.presentation.ui.components.template.ScaffoldBase
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.TablerosViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.TareasViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TablerosViewModel

class TablerosScreen(
    private val tableroFactory: TablerosViewModelFactory,
    private val tareasViewModelFactory: TareasViewModelFactory
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val tablerosViewModel: TablerosViewModel = rememberScreenModel { tableroFactory.create() }
        val idOrg = 1L

        LaunchedEffect(idOrg) {
            tablerosViewModel.cargarTableros(idOrg)
        }

        MenuTableros(
            tablerosViewModel = tablerosViewModel,
            onIrACrearTablero = { 
                navigator.push(MenuCrearTableroScreen(tableroFactory)) 
            },
            onVerTablero = { idTablero, nombreTablero ->
                navigator.push(
                    DetalleTableroScreen(
                        idTablero = idTablero,
                        nombreTablero = nombreTablero,
                        tareasViewModelFactory = tareasViewModelFactory
                    )
                )
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MenuTableros(
        tablerosViewModel: TablerosViewModel,
        onIrACrearTablero: () -> Unit,
        onVerTablero: (Long, String) -> Unit
    ) {
        val listaTableros by tablerosViewModel.listaTableros.collectAsStateWithLifecycle()

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
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(listaTableros) { tablero ->
                        CardTablero(
                            tablero,
                            onClick = {
                                onVerTablero(tablero.idTablero, tablero.titulo)
                            }
                        )
                    }
                }
            }
        }
    }
}
