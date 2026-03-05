package dev.wdona.burnt_out.presentation.viewmodel.viewmodels

import dev.wdona.burnt_out.data.dao.TareaRepository
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.domain.Tarea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TareaViewModel(repository: TareaRepository) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private val _uiState = MutableStateFlow<Tarea?>(null)
    val uiState: StateFlow<Tarea?> = _uiState.asStateFlow()
    private val _listaTareas = MutableStateFlow<List<Tarea>>(emptyList())
    val listaTareas: StateFlow<List<Tarea>> = _listaTareas

    fun agregarTarea(tarea: Tarea) {

    }


}