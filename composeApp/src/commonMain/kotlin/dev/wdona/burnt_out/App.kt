package dev.wdona.burnt_out

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dev.wdona.burnt_out.presentation.ui.pantallas.MainScreen
import dev.wdona.burnt_out.presentation.ui.theme.BurntOutMaterialTheme
import dev.wdona.burnt_out.presentation.ui.theme.DarkColorScheme
import dev.wdona.burnt_out.presentation.ui.theme.LightColorScheme
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    tareaFactory: TareasViewModelFactory,
    miEquipoViewModelFactory: MiEquipoViewModelFactory,
    miPerfilViewModelFactory: MiPerfilViewModelFactory,
    tablerosViewModelFactory: TablerosViewModelFactory,
    leaderboardViewModelFactory: LeaderboardViewModelFactory,
    ajustesViewModelFactory: AjustesViewModelFactory
) {
    BurntOutMaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BurntOutMaterialTheme.getColorScheme().background
        ) {
            Navigator(
                MainScreen(
                    tareaFactory = tareaFactory,
                    equipoFactory = miEquipoViewModelFactory,
                    perfilFactory = miPerfilViewModelFactory,
                    tableroFactory = tablerosViewModelFactory,
                    leaderboardFactory = leaderboardViewModelFactory,
                    ajustesFactory = ajustesViewModelFactory
                )
            ) { navigator ->
                PressBackHandler(enabled = navigator.canPop) {
                    navigator.pop()
                }
                SlideTransition(navigator)
            }
        }
    }
}
