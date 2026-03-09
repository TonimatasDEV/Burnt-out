package dev.wdona.burnt_out.data.api

import dev.wdona.burnt_out.domain.model.Ajuste

interface AjusteApi {
    fun anadirAjuste(ajuste: Ajuste)

    fun modificarAjuste(ajuste: Ajuste)

    fun eliminarAjuste(idAjuste: Long)
}