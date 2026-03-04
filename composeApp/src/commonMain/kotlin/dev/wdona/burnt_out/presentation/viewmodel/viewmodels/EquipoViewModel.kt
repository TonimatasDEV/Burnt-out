package dev.wdona.burnt_out.presentation.viewmodel.viewmodels

import dev.wdona.burnt_out.shared.domain.Equipo
import dev.wdona.burnt_out.shared.domain.Usuario
import dev.wdona.burnt_out.shared.db.DatabaseDriverFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EquipoViewModel(databaseDriverFactory: DatabaseDriverFactory) {
    // Crea un CoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    // Crea un MutableStateFlow (para actualizar el estado en la UI) privado y mutable para la respuesta
    private val _uiStateEquipo = MutableStateFlow<Equipo?>(null)

    // Crea un StateFlow publico y de solo lectura para la respuesta
    val uiStateEquipo: StateFlow<Equipo?> = _uiStateEquipo.asStateFlow()
    private val _listaEquipos = MutableStateFlow<List<Equipo>>(emptyList())
    val listaEquipos: StateFlow<List<Equipo>> = _listaEquipos

    private val _uiStateMiembro = MutableStateFlow<Usuario?>(null)
    val uiStateMiembro: StateFlow<Usuario?> =_uiStateMiembro.asStateFlow()
    private val _listaMiembros = MutableStateFlow<List<Usuario>>(emptyList())
    val listaMiembros: StateFlow<List<Usuario>?> = _listaMiembros


    fun crearEquipo(idEquipo: Long, nombreEquipo: String, idOrganizacion: Long) {
        val equipoLocal = Equipo(idEquipo, nombreEquipo, 0, idOrganizacion, emptyList())

        viewModelScope.launch {

        }
    }

    fun cargarEquiposPorOrganizacion(idOrganizacion: Long) {
        viewModelScope.launch {

        }
    }

    fun cargarEquipoPorId(idEquipo: Long) : Equipo? {
        viewModelScope.launch {

        }
        return _uiStateEquipo.value
    }

    fun cargarMiembrosEquipo(idEquipo: Long) {
        viewModelScope.launch {

        }
    }
}