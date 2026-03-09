package dev.wdona.burnt_out.presentation.viewmodel.viewmodels

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dev.wdona.burnt_out.domain.repository.EquipoRepository
import dev.wdona.burnt_out.domain.usecase.CargarMiembrosEquipo
import dev.wdona.burnt_out.shared.domain.Equipo
import dev.wdona.burnt_out.shared.domain.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MiEquipoViewModel(
    private val repository: EquipoRepository,
    private val cargarMiembrosEquipo: CargarMiembrosEquipo
) : ScreenModel {
    private val _uiStateEquipo = MutableStateFlow<Equipo?>(null)
    val uiStateEquipo: StateFlow<Equipo?> = _uiStateEquipo.asStateFlow()

    private val _listaEquipos = MutableStateFlow<List<Equipo>>(emptyList())
    val listaEquipos: StateFlow<List<Equipo>> = _listaEquipos.asStateFlow()

    private val _listaMiembros = MutableStateFlow<List<Usuario>>(emptyList())
    val listaMiembros: StateFlow<List<Usuario>> = _listaMiembros.asStateFlow()

    fun cargarEquipoPorId(idEquipo: Long) {
        screenModelScope.launch {
            _uiStateEquipo.value = repository.getEquipoById(idEquipo)
        }
    }

    fun sumarPuntos(idEquipo: Long, puntos: Long) {
        screenModelScope.launch {
            repository.updatePuntuacion(idEquipo, puntos)
            cargarEquipoPorId(idEquipo)
        }
    }

    fun cargarMiembrosEquipo(idEquipo: Long) {
        screenModelScope.launch {
            _listaMiembros.value = cargarMiembrosEquipo.invoke(idEquipo)
        }
    }
}
