package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.LeaderboardViewModel

expect class LeaderboardViewModelFactory {
    fun create(): LeaderboardViewModel
}
