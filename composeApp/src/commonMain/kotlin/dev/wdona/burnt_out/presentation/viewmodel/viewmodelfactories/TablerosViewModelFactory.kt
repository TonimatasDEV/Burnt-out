package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.TablerosViewModel

expect class TablerosViewModelFactory {
    fun create(): TablerosViewModel
}
