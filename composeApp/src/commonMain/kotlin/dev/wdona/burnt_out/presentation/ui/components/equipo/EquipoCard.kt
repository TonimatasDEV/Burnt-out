package dev.wdona.burnt_out.presentation.ui.components.equipo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.wdona.burnt_out.shared.domain.Equipo

@Composable
fun EquipoCard(equipo: Equipo, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(bottom = 8.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = equipo.titulo,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Puntos: ${equipo.puntuacion ?: 0}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}