package dev.wdona.burnt_out.data.dao

import dev.wdona.burnt_out.shared.domain.Tablero

interface TableroDao {
    suspend fun getTableroById(idTablero: Long): Tablero
    suspend fun getTablerosByOrg(idOrg: Long): List<Tablero>
    suspend fun crearTablero(tablero: Tablero): Long
    suspend fun actualizarTablero(tablero: Tablero): Boolean
    suspend fun eliminarTablero(idTablero: Long): Boolean
    suspend fun insertOrUpdateTablero(tablero: Tablero): Boolean
}
