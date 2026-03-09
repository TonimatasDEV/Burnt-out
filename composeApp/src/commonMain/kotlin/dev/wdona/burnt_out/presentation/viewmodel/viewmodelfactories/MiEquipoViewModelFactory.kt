package dev.wdona.burnt_out.presentation.viewmodel.viewmodelfactories

import dev.wdona.burnt_out.domain.usecase.CargarMiembrosEquipo
import dev.wdona.burnt_out.presentation.viewmodel.viewmodels.MiEquipoViewModel

expect class MiEquipoViewModelFactory {
    fun create(): MiEquipoViewModel
}