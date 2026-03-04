package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.EquipoViewModel

expect class EquipoViewModelFactory {
    fun create(): EquipoViewModel
}