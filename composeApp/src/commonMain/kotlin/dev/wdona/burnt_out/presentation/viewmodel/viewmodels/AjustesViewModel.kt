package dev.wdona.burnt_out.presentation.viewmodel.viewmodels

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dev.wdona.burnt_out.domain.model.Ajuste
import dev.wdona.burnt_out.domain.repository.AjusteRepository
import dev.wdona.burnt_out.shared.domain.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AjustesViewModel(private val repository: AjusteRepository) : ScreenModel {
    var _listaAjustes = MutableStateFlow<List<Ajuste?>>(emptyList())
    val listaAjustes = _listaAjustes.asStateFlow()
    var _uiStateUsuarioActual = MutableStateFlow<Usuario?>(null)
    val uiStateUsuarioActual = _uiStateUsuarioActual

    fun cargarAjustesUsuarioActual() {
        assert(uiStateUsuarioActual.value != null)

        screenModelScope.launch {
            _listaAjustes.value = repository.getAjustesByUsuario(uiStateUsuarioActual.value!!.idUsuario)
        }
    }

    fun cargarUsuarioActual(usuario: Usuario) {
        assert(uiStateUsuarioActual.value == null)

        uiStateUsuarioActual.value = usuario
    }


}