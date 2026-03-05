package dev.wdona.burnt_out.data.dao

import dev.wdona.burnt_out.shared.domain.Tablero

interface TableroDao {
    suspend fun getTableroById(idTablero: Long): Tablero
    suspend fun getTablerosByOrg(idOrg: Long): List<Tablero>
    suspend fun insertTablero(tablero: Tablero): Boolean
    suspend fun updateTablero(tablero: Tablero): Boolean
    suspend fun deleteTablero(idTablero: Long): Boolean
}
