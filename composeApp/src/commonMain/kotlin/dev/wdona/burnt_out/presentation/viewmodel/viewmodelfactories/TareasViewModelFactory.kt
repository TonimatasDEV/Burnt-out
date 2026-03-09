package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TareasViewModel

expect class TareasViewModelFactory {
    fun create(): TareasViewModel
}