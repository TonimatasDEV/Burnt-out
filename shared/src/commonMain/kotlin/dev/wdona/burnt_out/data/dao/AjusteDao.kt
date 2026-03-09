package dev.wdona.burnt_out.data.dao

import dev.wdona.burnt_out.domain.model.Ajuste

interface AjusteDao {
    fun anadirAjuste(ajuste: Ajuste)
    fun modificarAjuste(ajuste: Ajuste)
    fun eliminarAjuste(idAjuste: Long)
    fun getAjustesByUsuario(idUsuario: Long): List<Ajuste>
    fun getAjusteByIdYUsuario(idAjuste: Long, idUsuario: Long): Ajuste
}