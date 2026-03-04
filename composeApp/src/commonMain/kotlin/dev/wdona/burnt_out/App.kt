package dev.wdona.burnt_out

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import dev.wdona.burnt_out.presentation.pantallas.MenuInicio
import dev.wdona.burnt_out.presentation.theme.CustomMaterialTheme
import dev.wdona.burnt_out.presentation.theme.DarkColorScheme
import dev.wdona.burnt_out.presentation.theme.LightColorScheme
import dev.wdona.burnt_out.presentation.viewmodelfactories.EquipoViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodelfactories.PerfilViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodelfactories.TableroViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodelfactories.TareaViewModelFactory

import cafe.adriel.voyager.transitions.SlideTransition


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(tareaFactory: TareaViewModelFactory, equipoViewModelFactory: EquipoViewModelFactory, perfilViewModelFactory: PerfilViewModelFactory, tableroViewModelFactory: TableroViewModelFactory) {
    CustomMaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = if (isSystemInDarkTheme()) DarkColorScheme.background else LightColorScheme.background

        ) {
            Navigator(
                key = "home",
                screen = MenuInicio(equipoViewModelFactory, tableroViewModelFactory, perfilViewModelFactory, tareaFactory)
            ) {
                navigator -> SlideTransition(navigator)
            }

            // TODO: HACER INICIO DE SESION Y SI ESTA LOGEADO QUE APAREZCA EL HOME, SI NO EL LOGIN
        }
    }
}






