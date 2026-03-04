package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TareaViewModel

expect class TareaViewModelFactory {
    fun create(): TareaViewModel
}