package dev.wdona.burnt_out.presentation.ui.pantallas.tarea

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.presentation.ui.components.template.ScaffoldBase
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.TareaViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TareaViewModel
import dev.wdona.burnt_out.shared.domain.Tarea

class MenuCrearTareaScreen(val factory: TareaViewModelFactory, val idTablero: Long) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra
        val viewModel: TareaViewModel = rememberScreenModel { factory.create() }

        MenuCrearTareaContent(
            idTablero = idTablero,
            tareaViewModel = viewModel,
            onVolver = { navigator.pop() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCrearTareaContent(idTablero: Long, tareaViewModel: TareaViewModel, onVolver: () -> Unit) {
    var textStateNombreTarea by remember { mutableStateOf("") }
    var textStateDescripcion by remember { mutableStateOf("") }

    val ejecutarEnvio = {
        if (textStateNombreTarea.isNotBlank()) {
            val nuevaTarea = Tarea(
                idTarea = 0, // SQLite ignora este 0 y usa AUTOINCREMENT gracias a una nueva query insertTarea
                titulo = textStateNombreTarea,
                descripcion = textStateDescripcion,
                estado = "PENDIENTE",
                idTableroPerteneciente = idTablero,
                idUsuarioAsignado = 1, // TODO: Coger el usuario actual
                idSubtareas = emptyList()
            )
            tareaViewModel.crearTarea(nuevaTarea)

            textStateNombreTarea = ""
            textStateDescripcion = ""
            onVolver()
        }
    }

    ScaffoldBase(
        titulo = "Nueva Tarea", 
        onVolver = onVolver, 
        onCrear = ejecutarEnvio, 
        textoFABCrear = "Crear Tarea"
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = textStateNombreTarea,
                onValueChange = { textStateNombreTarea = it },
                label = { Text("Título") },
                placeholder = { Text("Hacer la compra...") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            OutlinedTextField(
                value = textStateDescripcion,
                onValueChange = { textStateDescripcion = it },
                label = { Text("Descripción") },
                placeholder = { Text("Detalles de la tarea...") },
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { ejecutarEnvio() }
                ),
                singleLine = false
            )
        }
    }
}
