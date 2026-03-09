package dev.wdona.burnt_out.data.datasource.local.impl

import dev.wdona.burnt_out.data.dao.AjusteDao
import dev.wdona.burnt_out.data.datasource.local.AjusteLocalDataSource
import dev.wdona.burnt_out.domain.model.Ajuste

class AjusteLocalDataSourceImpl(private val dao: AjusteDao) : AjusteLocalDataSource {
    override fun eliminarAjuste(idAjuste: Long) {
        dao.eliminarAjuste(idAjuste)
    }

    override fun anadirAjuste(ajuste: Ajuste) {
        dao.anadirAjuste(ajuste)
    }

    override fun modificarAjuste(ajuste: Ajuste) {
        dao.modificarAjuste(ajuste)
    }

    override fun getAjustesByUsuario(idUsuario: Long): List<Ajuste> {
        return dao.getAjustesByUsuario(idUsuario)
    }

    override fun getAjusteByIdYUsuario(
        idAjuste: Long,
        idUsuario: Long
    ): Ajuste {
        return dao.getAjusteByIdYUsuario(idAjuste, idUsuario)
    }

}