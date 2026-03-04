package dev.wdona.burnt_out.presentation.viewmodel.viewmodels

import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.domain.Tarea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TareaViewModel(databaseDriverFactory: DatabaseDriverFactory) {
    // Crea un CoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    // Crea un MutableStateFlow (para actualizar el estado en la UI) privado y mutable para la respuesta
    private val _uiState = MutableStateFlow<Tarea?>(null)

    // Crea un StateFlow publico y de solo lectura para la respuesta
    val uiState: StateFlow<Tarea?> = _uiState.asStateFlow()
    private val _listaTareas = MutableStateFlow<List<Tarea>>(emptyList())
    val listaTareas: StateFlow<List<Tarea>> = _listaTareas

}