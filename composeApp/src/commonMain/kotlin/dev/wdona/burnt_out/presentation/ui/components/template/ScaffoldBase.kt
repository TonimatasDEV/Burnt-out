package dev.wdona.burnt_out.presentation.ui.components.template

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import dev.wdona.burnt_out.presentation.ui.components.common.FABCrear
import dev.wdona.burnt_out.presentation.ui.components.common.InfoTopBarCustomOnVolver
import dev.wdona.burnt_out.presentation.ui.components.common.InfoTopBarCustomTitle

@Composable
fun ScaffoldBase(
    titulo: String = "",
    onVolver: () -> Unit = {},
    onCrear: () -> Unit= {},
    textoFABCrear: String? = null,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            if (onVolver != {}) {
                InfoTopBarCustomOnVolver(titulo ?: "", onVolver)
            } else {
                InfoTopBarCustomTitle(titulo ?: "")
            }
        },
        floatingActionButton = {
            if (onCrear != {}) {
                FABCrear(textoFABCrear ?: "Nuevo", onCrear)
            }
        },
        bottomBar = bottomBar
    ) { paddingValues ->
        content(paddingValues)
    }
}