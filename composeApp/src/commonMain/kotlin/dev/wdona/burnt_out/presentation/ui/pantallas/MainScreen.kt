package dev.wdona.burnt_out.presentation.ui.pantallas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.*
import cafe.adriel.voyager.transitions.SlideTransition
import dev.wdona.burnt_out.presentation.ui.components.template.ScaffoldBase
import dev.wdona.burnt_out.presentation.ui.pantallas.equipo.EquipoScreen
import dev.wdona.burnt_out.presentation.ui.pantallas.equipo.LeaderboardScreen
import dev.wdona.burnt_out.presentation.ui.pantallas.perfil.PerfilScreen
import dev.wdona.burnt_out.presentation.ui.pantallas.tablero.TablerosScreen
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.*

class MainScreen(
    private val tareaFactory: TareaViewModelFactory,
    private val equipoFactory: EquipoViewModelFactory,
    private val perfilFactory: PerfilViewModelFactory,
    private val tableroFactory: TableroViewModelFactory,
    private val leaderboardFactory: LeaderboardViewModelFactory
) : Screen {

    @Composable
    override fun Content() {
        val tablerosTab = remember { TablerosTab(tableroFactory, tareaFactory) }
        val equipoTab = remember { EquipoTab(equipoFactory) }
        val leaderboardTab = remember { LeaderboardTab(leaderboardFactory) }
        val perfilTab = remember { PerfilTab(perfilFactory) }

        TabNavigator(tablerosTab) {
            ScaffoldBase(
                bottomBar = {
                    NavigationBar {
                        TabNavigationItem(tablerosTab)
                        TabNavigationItem(equipoTab)
                        TabNavigationItem(leaderboardTab)
                        TabNavigationItem(perfilTab)
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    CurrentTab()
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        label = { Text(tab.options.title) },
        icon = {
            val icon = tab.options.icon ?: rememberVectorPainter(Icons.Default.Home)
            Icon(painter = icon, contentDescription = tab.options.title)
        }
    )
}

private class TablerosTab(val factory: TableroViewModelFactory, val tareaFactory: TareaViewModelFactory) : Tab {
    override val key = "TablerosTab"
    override val options: TabOptions
        @Composable
        get() = TabOptions(index = 0u, title = "Tableros", icon = rememberVectorPainter(Icons.Default.Home))

    @Composable
    override fun Content() {
        Navigator(TablerosScreen(factory, tareaFactory)) { navigator ->
            SlideTransition(navigator)
        }
    }
}

private class EquipoTab(val factory: EquipoViewModelFactory) : Tab {
    override val key = "EquipoTab"
    override val options: TabOptions
        @Composable
        get() = TabOptions(index = 1u, title = "Equipo", icon = rememberVectorPainter(Icons.Default.Groups))

    @Composable
    override fun Content() {
        Navigator(EquipoScreen(factory)) { navigator ->
            SlideTransition(navigator)
        }
    }
}

private class LeaderboardTab(val factory: LeaderboardViewModelFactory) : Tab {
    override val key = "LeaderboardTab"
    override val options: TabOptions
        @Composable
        get() = TabOptions(index = 2u, title = "Ranking", icon = rememberVectorPainter(Icons.Default.EmojiEvents))

    @Composable
    override fun Content() {
        Navigator(LeaderboardScreen(factory, 1L)) { navigator ->
            SlideTransition(navigator)
        }
    }
}

private class PerfilTab(val factory: PerfilViewModelFactory) : Tab {
    override val key = "PerfilTab"
    override val options: TabOptions
        @Composable
        get() = TabOptions(index = 3u, title = "Perfil", icon = rememberVectorPainter(Icons.Default.AccountCircle))

    @Composable
    override fun Content() {
        Navigator(PerfilScreen(factory)) { navigator ->
            SlideTransition(navigator)
        }
    }
}
