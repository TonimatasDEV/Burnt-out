package dev.wdona.burnt_out.data.datasource.local

import dev.wdona.burnt_out.data.datasource.common.AjusteDataSource
import dev.wdona.burnt_out.domain.model.Ajuste

interface AjusteLocalDataSource : AjusteDataSource {
    fun eliminarAjuste(idAjuste: Long)
    fun anadirAjuste(ajuste: Ajuste)
}