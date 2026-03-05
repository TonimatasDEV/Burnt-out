package dev.wdona.burnt_out.presentation.viewmodel.viewmodels

import dev.wdona.burnt_out.data.dao.TareaRepository
import dev.wdona.burnt_out.shared.domain.Tarea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TareaViewModel(private val repository: TareaRepository) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _uiState = MutableStateFlow<Tarea?>(null)
    val uiState: StateFlow<Tarea?> = _uiState.asStateFlow()

    private val _listaTareas = MutableStateFlow<List<Tarea>>(emptyList())
    val listaTareas: StateFlow<List<Tarea>> = _listaTareas

    fun cargarTareas(tableroId: Long) {
        viewModelScope.launch {
            _listaTareas.value = repository.getTareasByTableroId(tableroId)
        }
    }

    fun cargarTareaPorId(idTarea: Long, idTablero: Long) {
        viewModelScope.launch {
            _uiState.value = repository.getTareaById(idTarea, idTablero)
        }
    }

    fun crearTarea(tarea: Tarea) {
        viewModelScope.launch {
            repository.crearTarea(tarea)
            cargarTareas(tarea.idTableroPerteneciente)
        }
    }

    fun actualizarTarea(tarea: Tarea) {
        viewModelScope.launch {
            repository.actualizarTarea(tarea)
            cargarTareas(tarea.idTableroPerteneciente)
        }
    }

    fun eliminarTarea(idTarea: Long, tableroId: Long) {
        viewModelScope.launch {
            repository.eliminarTarea(idTarea)
            cargarTareas(tableroId)
        }
    }
}
