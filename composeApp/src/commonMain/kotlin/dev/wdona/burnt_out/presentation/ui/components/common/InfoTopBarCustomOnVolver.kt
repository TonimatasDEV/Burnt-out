package dev.wdona.burnt_out.presentation.ui.components.common

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun InfoTopBarCustomOnVolver(
    title: String,
    onVolver: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            BotonVolver { onVolver() }
        },
        windowInsets = WindowInsets(0, 0, 0, 0)
    )
}