package dev.wdona.burnt_out.data.datasource.local.impl

import dev.wdona.burnt_out.data.dao.TableroDao
import dev.wdona.burnt_out.data.datasource.local.TableroLocalDataSource
import dev.wdona.burnt_out.shared.domain.Tablero

class TableroLocalDataSourceImpl(private val tableroDao: TableroDao) : TableroLocalDataSource {
    override suspend fun getTableroById(idTablero: Long): Tablero? {
        return tableroDao.getTableroById(idTablero)
    }

    override suspend fun getTablerosByOrg(idOrg: Long): List<Tablero> {
        return tableroDao.getTablerosByOrg(idOrg)
    }

    override suspend fun crearTablero(tablero: Tablero): Boolean {
        return tableroDao.insertTablero(tablero)
    }

    override suspend fun actualizarTablero(tablero: Tablero): Boolean {
        return tableroDao.updateTablero(tablero)
    }

    override suspend fun eliminarTablero(idTablero: Long): Boolean {
        return tableroDao.deleteTablero(idTablero)
    }

    override suspend fun eliminarTablerosPorOrg(idOrg: Long) {
        val tableros = tableroDao.getTablerosByOrg(idOrg)
        tableros.forEach { tableroDao.deleteTablero(it.idTablero) }
    }
}
