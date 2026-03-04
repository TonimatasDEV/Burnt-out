package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TableroViewModel

expect class TableroViewModelFactory {
    fun create(): TableroViewModel
}
