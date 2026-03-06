package dev.wdona.burnt_out.data.datasource.local.impl

import dev.wdona.burnt_out.data.dao.EquipoDao
import dev.wdona.burnt_out.data.datasource.local.EquipoLocalDataSource
import dev.wdona.burnt_out.shared.domain.Equipo

class EquipoLocalDataSourceImpl(private val equipoDao: EquipoDao) : EquipoLocalDataSource {
    override suspend fun getEquipoById(idEquipo: Long): Equipo {
        return equipoDao.getEquipoById(idEquipo)
    }

    override suspend fun getEquiposByOrg(idOrg: Long): List<Equipo> {
        return equipoDao.getEquiposByOrg(idOrg)
    }

    override suspend fun crearEquipo(equipo: Equipo): Long {
        return equipoDao.crearEquipo(equipo)
    }

    override suspend fun actualizarEquipo(equipo: Equipo): Boolean {
        return equipoDao.actualizarEquipo(equipo)
    }

    override suspend fun eliminarEquipo(idEquipo: Long): Boolean {
        return equipoDao.eliminarEquipo(idEquipo)
    }

    override suspend fun insertOrUpdateEquipo(equipo: Equipo): Boolean {
        return equipoDao.insertOrUpdateEquipo(equipo)
    }

    override suspend fun updatePuntuacion(idEquipo: Long, puntos: Long): Boolean {
        return equipoDao.updatePuntuacion(idEquipo, puntos)
    }

    override suspend fun eliminarEquiposPorOrg(idOrg: Long) {
        val equipos = equipoDao.getEquiposByOrg(idOrg)
        equipos.forEach { equipoDao.eliminarEquipo(it.idEquipo) }
    }
}
