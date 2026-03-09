package dev.wdona.burnt_out.data.datasource.common

import dev.wdona.burnt_out.domain.model.Ajuste

interface AjusteDataSource {
    fun modificarAjuste(ajuste: Ajuste)
    fun getAjustesByUsuario(idUsuario: Long): List<Ajuste>
    fun getAjusteByIdYUsuario(idAjuste: Long, idUsuario: Long): Ajuste
}