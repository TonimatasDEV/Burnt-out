package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.PerfilViewModel

expect class PerfilViewModelFactory {
    fun create(): PerfilViewModel
}