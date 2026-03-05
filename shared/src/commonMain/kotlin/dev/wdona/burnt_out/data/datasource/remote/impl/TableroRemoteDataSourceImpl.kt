package dev.wdona.burnt_out.data.datasource.remote.impl

import dev.wdona.burnt_out.data.api.TableroApi
import dev.wdona.burnt_out.data.datasource.remote.TableroRemoteDataSource
import dev.wdona.burnt_out.shared.domain.Tablero

class TableroRemoteDataSourceImpl(private val tableroApi: TableroApi) : TableroRemoteDataSource {
    override suspend fun getTableroById(idTablero: Long): Tablero {
        return tableroApi.getTableroById(idTablero)
    }

    override suspend fun getTablerosByOrg(idOrg: Long): List<Tablero> {
        return tableroApi.getTablerosByOrg(idOrg)
    }

    override suspend fun crearTablero(tablero: Tablero): Boolean {
        return tableroApi.crearTablero(tablero).status.value in 200..299
    }

    override suspend fun actualizarTablero(tablero: Tablero): Boolean {
        return tableroApi.actualizarTablero(tablero).status.value in 200..299
    }

    override suspend fun eliminarTablero(idTablero: Long): Boolean {
        return tableroApi.eliminarTablero(idTablero).status.value in 200..299
    }
}
