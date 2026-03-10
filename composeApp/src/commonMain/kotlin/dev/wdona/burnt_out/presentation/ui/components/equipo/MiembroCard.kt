package dev.wdona.burnt_out.presentation.ui.components.equipo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.wdona.burnt_out.shared.domain.Usuario

@Composable
fun MiembroCard(miembro: Usuario, onClick: () -> Unit) {
    val id = if (miembro.idUsuario == Long.MIN_VALUE) {
        ""
    } else {
        miembro.idUsuario.toString() + ""
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(bottom = 8.dp)
            .clickable { onClick() },

        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "$id ${miembro.nombre}",
            style = MaterialTheme.typography.titleMedium

        )
    }
}
