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
import dev.wdona.burnt_out.presentation.ui.theme.CustomMaterialTheme
import dev.wdona.burnt_out.presentation.ui.theme.DarkColorScheme
import dev.wdona.burnt_out.presentation.ui.theme.LightColorScheme
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    tareaFactory: TareaViewModelFactory,
    equipoViewModelFactory: EquipoViewModelFactory,
    perfilViewModelFactory: PerfilViewModelFactory,
    tableroViewModelFactory: TableroViewModelFactory,
    leaderboardViewModelFactory: LeaderboardViewModelFactory
) {
    CustomMaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = if (isSystemInDarkTheme()) DarkColorScheme.background else LightColorScheme.background
        ) {
            Navigator(
                MainScreen(
                    tareaFactory = tareaFactory,
                    equipoFactory = equipoViewModelFactory,
                    perfilFactory = perfilViewModelFactory,
                    tableroFactory = tableroViewModelFactory,
                    leaderboardFactory = leaderboardViewModelFactory
                )
            ) { navigator ->
                SlideTransition(navigator)
            }
        }
    }
}
