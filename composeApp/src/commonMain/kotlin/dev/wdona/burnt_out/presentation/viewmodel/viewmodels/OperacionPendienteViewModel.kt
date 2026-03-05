package dev.wdona.burnt_out.presentation.viewmodel.viewmodels

import dev.wdona.burnt_out.domain.model.OperacionPendiente
import dev.wdona.burnt_out.domain.repository.OperacionesPendientesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OperacionPendienteViewModel(private val repository: OperacionesPendientesRepository) {
    private val _viewModelScope = CoroutineScope(Dispatchers.Main)
    private val _uiState = MutableStateFlow<OperacionPendiente?>(null)
    val uiState = _uiState.asStateFlow()
    private val _listaOperaciones = MutableStateFlow<List<OperacionPendiente>>(emptyList())
    val listaOperaciones = _listaOperaciones
}
