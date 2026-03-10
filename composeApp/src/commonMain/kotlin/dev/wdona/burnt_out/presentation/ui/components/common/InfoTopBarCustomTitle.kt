package dev.wdona.burnt_out.presentation.ui.components.common

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun InfoTopBarCustomTitle(
    title: String,
    onAjustes: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        actions = {
            if (onAjustes != null) {
                BotonAjustes { onAjustes() }
            }
        },
        windowInsets = WindowInsets(0, 0, 0, 0)
    )
}
