package dev.wdona.burnt_out.presentation.ui.components.template

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.wdona.burnt_out.presentation.ui.components.common.FABCrear
import dev.wdona.burnt_out.presentation.ui.components.common.InfoTopBarCustomOnVolver
import dev.wdona.burnt_out.presentation.ui.components.common.InfoTopBarCustomTitle

@Composable
fun ScaffoldBase(
    titulo: String = "",
    onVolver: (() -> Unit)? = null,
    onAjustes: (() -> Unit)? = null,
    onCrear: (() -> Unit)? = null,
    textoFABCrear: String? = null,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            if (onVolver != null) {
                InfoTopBarCustomOnVolver(titulo, onVolver, onAjustes)
            } else {
                InfoTopBarCustomTitle(titulo, onAjustes)
            }
        },
        floatingActionButton = {
            if (onCrear != null) {
                FABCrear(textoFABCrear ?: "Nuevo", onCrear)
            }
        },
        bottomBar = bottomBar
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
        ) {
            content(PaddingValues(0.dp))
        }
    }
}
