package dev.wdona.burnt_out.presentation.viewmodelfactories

import dev.wdona.burnt_out.presentation.viewmodels.PerfilViewModel

expect class PerfilViewModelFactory {
    fun create(): PerfilViewModel
}