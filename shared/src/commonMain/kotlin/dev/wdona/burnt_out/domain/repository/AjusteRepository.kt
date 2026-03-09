package dev.wdona.burnt_out.domain.repository

import dev.wdona.burnt_out.domain.model.Ajuste

interface AjusteRepository {
    fun modificarAjuste(ajuste: Ajuste)
    fun getAjustesByUsuario(idUsuario: Long): List<Ajuste>
    fun getAjusteByIdYUsuario(idAjuste: Long, idUsuario: Long): Ajuste
}