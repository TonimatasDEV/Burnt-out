package dev.wdona.burnt_out.presentation.ui.pantallas.tarea

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import dev.wdona.burnt_out.domain.model.TipoEstadoTarea
import dev.wdona.burnt_out.presentation.ui.components.template.ScaffoldBase
import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.TareasViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TareasViewModel
import dev.wdona.burnt_out.shared.domain.Tarea
import dev.wdona.burnt_out.shared.utils.SettingsManager

class MenuCrearTareaScreen(val factory: TareasViewModelFactory, val idTablero: Long) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: TareasViewModel = rememberScreenModel { factory.create() }

        MenuCrearTareaContent(
            idTablero = idTablero,
            tareasViewModel = viewModel,
            onVolver = { navigator.pop() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCrearTareaContent(idTablero: Long, tareasViewModel: TareasViewModel, onVolver: () -> Unit) {
    var textStateNombreTarea by remember { mutableStateOf("") }
    var textStateDescripcion by remember { mutableStateOf("") }

    var estadoExpanded by remember { mutableStateOf(false) }
    var estadoSelected by remember { mutableStateOf(TipoEstadoTarea.PENDIENTE) }


    val ejecutarEnvio = {
        if (textStateNombreTarea.isNotBlank()) {
            val nuevaTarea = Tarea(
                idTarea = 0,
                titulo = textStateNombreTarea,
                descripcion = textStateDescripcion,
                estado = estadoSelected.string,
                idTableroPerteneciente = idTablero,
                idUsuarioAsignado = SettingsManager.getIdUsuarioActual(),
                idSubtareas = emptyList()
            )
            tareasViewModel.crearTarea(nuevaTarea)

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
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            // Selector de Estado
            ExposedDropdownMenuBox(
                expanded = estadoExpanded,
                onExpandedChange = { estadoExpanded = !estadoExpanded },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                OutlinedTextField(
                    value = estadoSelected.string,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Estado") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = estadoExpanded) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = estadoExpanded,
                    onDismissRequest = { estadoExpanded = false }
                ) {
                    TipoEstadoTarea.entries.forEach { estado ->
                        DropdownMenuItem(
                            text = { Text(estado.string) },
                            onClick = {
                                estadoSelected = estado
                                estadoExpanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = textStateDescripcion,
                onValueChange = { textStateDescripcion = it },
                label = { Text("Descripción") },
                placeholder = { Text("Detalles de la tarea...") },
                modifier = Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { ejecutarEnvio() }
                ),
                singleLine = false
            )
        }
    }
}
