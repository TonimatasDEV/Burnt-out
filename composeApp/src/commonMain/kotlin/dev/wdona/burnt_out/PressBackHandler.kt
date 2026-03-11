package dev.wdona.burnt_out

import androidx.compose.runtime.Composable

@Composable
expect fun PressBackHandler(enabled: Boolean, onBack: () -> Unit)