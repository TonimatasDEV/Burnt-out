package dev.wdona.burnt_out.presentation.viewmodel.viewmodels

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dev.wdona.burnt_out.domain.repository.EquipoRepository
import dev.wdona.burnt_out.shared.domain.Equipo
import dev.wdona.burnt_out.shared.domain.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EquipoViewModel(private val repository: EquipoRepository) : ScreenModel {
    private val _uiStateEquipo = MutableStateFlow<Equipo?>(null)
    val uiStateEquipo: StateFlow<Equipo?> = _uiStateEquipo.asStateFlow()

    private val _listaEquipos = MutableStateFlow<List<Equipo>>(emptyList())
    val listaEquipos: StateFlow<List<Equipo>> = _listaEquipos.asStateFlow()

    private val _listaMiembros = MutableStateFlow<List<Usuario>>(emptyList())
    val listaMiembros: StateFlow<List<Usuario>> = _listaMiembros.asStateFlow()

    fun cargarEquipos(idOrg: Long) {
        screenModelScope.launch {
            _listaEquipos.value = repository.getEquiposByOrg(idOrg)
        }
    }

    fun cargarEquipoPorId(idEquipo: Long) {
        screenModelScope.launch {
            _uiStateEquipo.value = repository.getEquipoById(idEquipo)
        }
    }

    fun crearEquipo(equipo: Equipo) {
        screenModelScope.launch {
            repository.crearEquipo(equipo)
            cargarEquipos(equipo.idOrganizacion)
        }
    }

    fun actualizarEquipo(equipo: Equipo) {
        screenModelScope.launch {
            repository.actualizarEquipo(equipo)
            cargarEquipos(equipo.idOrganizacion)
        }
    }

    fun eliminarEquipo(idEquipo: Long, idOrg: Long) {
        screenModelScope.launch {
            repository.eliminarEquipo(idEquipo)
            cargarEquipos(idOrg)
        }
    }

    fun cargarMiembrosEquipo(idEquipo: Long) {
        screenModelScope.launch {
            _listaMiembros.value = repository.getMiembrosEquipo(idEquipo)
        }
    }

    fun sumarPuntos(idEquipo: Long, puntos: Long) {
        screenModelScope.launch {
            repository.updatePuntuacion(idEquipo, puntos)
            cargarEquipoPorId(idEquipo)
        }
    }
}
