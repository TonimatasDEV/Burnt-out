package dev.wdona.burnt_out.presentation.viewmodel.viewmodels

import dev.wdona.burnt_out.data.dao.TableroRepository
import dev.wdona.burnt_out.shared.domain.Tablero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TableroViewModel(private val repository: TableroRepository) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _uiState = MutableStateFlow<Tablero?>(null)
    val uiState: StateFlow<Tablero?> = _uiState.asStateFlow()

    private val _listaTableros = MutableStateFlow<List<Tablero>>(emptyList())
    val listaTableros: StateFlow<List<Tablero>> = _listaTableros
}
