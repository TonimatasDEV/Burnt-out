package dev.wdona.burnt_out.presentation.ui.pantallas

import androidx.compose.animation.*
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
import dev.wdona.burnt_out.presentation.ui.pantallas.equipo.EquipoScreen
import dev.wdona.burnt_out.presentation.ui.pantallas.equipo.LeaderboardScreen
import dev.wdona.burnt_out.presentation.ui.pantallas.perfil.PerfilScreen
import dev.wdona.burnt_out.presentation.ui.pantallas.tablero.TablerosScreen
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.*
import dev.wdona.burnt_out.shared.utils.SettingsManager

class MainScreen(
    private val tareaFactory: TareasViewModelFactory,
    private val equipoFactory: MiEquipoViewModelFactory,
    private val perfilFactory: MiPerfilViewModelFactory,
    private val tableroFactory: TablerosViewModelFactory,
    private val leaderboardFactory: LeaderboardViewModelFactory,
    private val ajustesFactory: AjustesViewModelFactory
) : Screen {

    @Composable
    override fun Content() {
        val tablerosTab = remember { TablerosTab(tableroFactory, tareaFactory) }
        val equipoTab = remember { EquipoTab(equipoFactory, perfilFactory, ajustesFactory) }
        val leaderboardTab = remember { LeaderboardTab(leaderboardFactory, ajustesFactory, equipoFactory, perfilFactory) }
        val perfilTab = remember { PerfilTab(perfilFactory, ajustesFactory) }

        TabNavigator(tablerosTab) { tabNavigator ->
            Scaffold(
                content = { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        AnimatedContent(
                            targetState = tabNavigator.current,
                            transitionSpec = {
                                fun getIndiceTab(tab: Tab): Int {
                                    return when (tab) {
                                        is TablerosTab -> 0
                                        is EquipoTab -> 1
                                        is LeaderboardTab -> 2
                                        is PerfilTab -> 3
                                        else -> 0
                                    }
                                }

                                val indiceTarget = getIndiceTab(targetState)
                                val indiceInicial = getIndiceTab(initialState)

                                val direccion = if (indiceTarget > indiceInicial) {
                                    AnimatedContentTransitionScope.SlideDirection.Left
                                } else {
                                    AnimatedContentTransitionScope.SlideDirection.Right
                                }
                                slideIntoContainer(direccion) togetherWith slideOutOfContainer(direccion)
                            },
                            label = "TransicionTab"
                        ) { tab ->
                            tabNavigator.saveableState(tab.key) {
                                tab.Content()
                            }
                        }
                    }
                },
                bottomBar = {
                    NavigationBar {
                        TabNavigationItem(tablerosTab, tabNavigator)
                        TabNavigationItem(equipoTab, tabNavigator)
                        TabNavigationItem(leaderboardTab, tabNavigator)
                        TabNavigationItem(perfilTab, tabNavigator)
                    }
                }
            )
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(
    tab: Tab,
    tabNavigator: TabNavigator
) {
    val isSelected = tabNavigator.current.key == tab.key

    NavigationBarItem(
        selected = isSelected,
        onClick = {
            if (!isSelected) {
                tabNavigator.current = tab
            }
        },
        label = { Text(tab.options.title) },
        icon = {
            val icon = tab.options.icon ?: rememberVectorPainter(Icons.Default.Home)
            Icon(painter = icon, contentDescription = tab.options.title)
        }
    )
}

private class TablerosTab(
    val factory: TablerosViewModelFactory,
    val tareaFactory: TareasViewModelFactory
) : Tab {
    override val key = "TablerosTab"
    @get:Composable
    override val options: TabOptions
        get() = TabOptions(index = 0u, title = "Tableros", icon = rememberVectorPainter(Icons.Default.Home))

    @Composable
    override fun Content() {
        Navigator(TablerosScreen(factory, tareaFactory)) { navigator ->
            SlideTransition(navigator)
        }
    }
}

private class EquipoTab(
    val factory: MiEquipoViewModelFactory,
    val perfilFactory: MiPerfilViewModelFactory,
    val settingsFactory: AjustesViewModelFactory
) : Tab {
    override val key = "EquipoTab"
    @get:Composable
    override val options: TabOptions
        get() = TabOptions(index = 1u, title = "Equipo", icon = rememberVectorPainter(Icons.Default.Groups))

    @Composable
    override fun Content() {
        Navigator(EquipoScreen(factory, perfilFactory, settingsFactory)) { navigator ->
            SlideTransition(navigator)
        }
    }
}

private class LeaderboardTab(
    val factory: LeaderboardViewModelFactory,
    val settingsFactory: AjustesViewModelFactory,
    val equipoFactory: MiEquipoViewModelFactory,
    val perfilFactory: MiPerfilViewModelFactory
) : Tab {
    override val key = "LeaderboardTab"
    @get:Composable
    override val options: TabOptions
        get() = TabOptions(index = 2u, title = "Ranking", icon = rememberVectorPainter(Icons.Default.EmojiEvents))

    @Composable
    override fun Content() {
        Navigator(LeaderboardScreen(
            factory, SettingsManager.getIdOrganizacionActual(),
            equipoFactory = equipoFactory,
            perfilFactory = perfilFactory,
            ajustesFactory = settingsFactory
        )) { navigator ->
            SlideTransition(navigator)
        }
    }
}

private class PerfilTab(
    val factory: MiPerfilViewModelFactory,
    val ajustesFactory: AjustesViewModelFactory
) : Tab {
    override val key = "PerfilTab"
    @get:Composable
    override val options: TabOptions
        get() = TabOptions(index = 3u, title = "Perfil", icon = rememberVectorPainter(Icons.Default.AccountCircle))

    @Composable
    override fun Content() {
        Navigator(PerfilScreen(factory, ajustesFactory)) { navigator ->
            SlideTransition(navigator)
        }
    }
}
