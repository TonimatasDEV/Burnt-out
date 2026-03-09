package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.OperacionesPendientesViewModel

expect class OperacionesPendientesViewModelFactory {
    fun create(): OperacionesPendientesViewModel
}
