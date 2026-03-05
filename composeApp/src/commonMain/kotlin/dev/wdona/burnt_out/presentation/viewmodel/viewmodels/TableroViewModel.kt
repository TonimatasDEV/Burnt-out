package dev.wdona.burnt_out.presentation.viewmodel.viewmodels

import dev.wdona.burnt_out.domain.repository.TableroRepository
import dev.wdona.burnt_out.shared.domain.Tablero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TableroViewModel(private val repository: TableroRepository) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _uiState = MutableStateFlow<Tablero?>(null)
    val uiState: StateFlow<Tablero?> = _uiState.asStateFlow()

    private val _listaTableros = MutableStateFlow<List<Tablero>>(emptyList())
    val listaTableros: StateFlow<List<Tablero>> = _listaTableros

    fun cargarTableros(idOrg: Long) {
        viewModelScope.launch {
            _listaTableros.value = repository.getTablerosByOrg(idOrg)
        }
    }

    fun cargarTableroPorId(idTablero: Long) {
        viewModelScope.launch {
            _uiState.value = repository.getTableroById(idTablero)
        }
    }

    fun crearTablero(tablero: Tablero) {
        viewModelScope.launch {
            repository.crearTablero(tablero)
            cargarTableros(tablero.idOrganizacion)
        }
    }

    fun actualizarTablero(tablero: Tablero) {
        viewModelScope.launch {
            repository.actualizarTablero(tablero)
            cargarTableros(tablero.idOrganizacion)
        }
    }

    fun eliminarTablero(idTablero: Long, idOrg: Long) {
        viewModelScope.launch {
            repository.eliminarTablero(idTablero)
            cargarTableros(idOrg)
        }
    }
}
