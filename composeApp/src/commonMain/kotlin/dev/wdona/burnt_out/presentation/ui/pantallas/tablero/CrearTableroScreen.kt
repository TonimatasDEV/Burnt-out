package dev.wdona.burnt_out.presentation.ui.pantallas.tablero

import dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories.TableroViewModelFactory
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TableroViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import dev.wdona.burnt_out.presentation.ui.components.template.CrearTemplate
import dev.wdona.burnt_out.shared.domain.Tablero


class MenuCrearTableroScreen(val factory: TableroViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra
        val viewModel: TableroViewModel = rememberScreenModel { factory.create() }

        MenuCrearTableroContent(
            tableroViewModel = viewModel,
            onVolver = { navigator.pop() }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCrearTableroContent(tableroViewModel: TableroViewModel, onVolver: () -> Unit) {
    var textStateNombreTablero by remember { mutableStateOf("") }

    val navigator = LocalNavigator.currentOrThrow

    val ejecutarEnvio: () -> Unit = {
        if (textStateNombreTablero.isNotBlank()) {
            tableroViewModel.crearTablero(
                Tablero(
                    0L,
                    textStateNombreTablero,
                    1L, // FIXME
                    1L) // FIXME
            )

            textStateNombreTablero = ""
            onVolver()

        }

    }
    CrearTemplate(
        titulo = "Crear Tablero",
        onVolver = onVolver,
        onCrear = ejecutarEnvio
    ) {
     paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = textStateNombreTablero,
                onValueChange = { newText ->
                    textStateNombreTablero = newText },
                label = { Text("Titulo") },
                placeholder = { Text("Ej. Lista de la compra") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
        }
    }
}