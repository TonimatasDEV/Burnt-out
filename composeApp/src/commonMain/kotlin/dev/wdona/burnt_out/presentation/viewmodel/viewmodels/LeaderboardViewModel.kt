package dev.wdona.burnt_out.presentation.viewmodel.viewmodels

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dev.wdona.burnt_out.domain.repository.EquipoRepository
import dev.wdona.burnt_out.shared.domain.Equipo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LeaderboardViewModel(private val repository: EquipoRepository) : ScreenModel {
    private val _leaderboard = MutableStateFlow<List<Equipo>>(emptyList())
    val leaderboard = _leaderboard.asStateFlow()

    fun cargarLeaderboard(idOrg: Long) {
        screenModelScope.launch {
            // FIXME
            _leaderboard.value = repository.getEquiposByOrg(idOrg).sortedByDescending { it.puntuacion }
        }
    }
}
