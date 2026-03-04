package dev.wdona.burnt_out.presentation.viewmodelfactories

import dev.wdona.burnt_out.presentation.viewmodels.TareaViewModel

expect class TareaViewModelFactory {
    fun create(): TareaViewModel
}