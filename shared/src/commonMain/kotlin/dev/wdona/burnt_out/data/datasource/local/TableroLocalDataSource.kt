package dev.wdona.burnt_out.data.datasource.local

import dev.wdona.burnt_out.shared.domain.Tablero

interface TableroLocalDataSource {
    suspend fun getTableroById(idTablero: Long): Tablero?
    suspend fun getTablerosByOrg(idOrg: Long): List<Tablero>
    suspend fun crearTablero(tablero: Tablero): Boolean
    suspend fun actualizarTablero(tablero: Tablero): Boolean
    suspend fun eliminarTablero(idTablero: Long): Boolean
    suspend fun eliminarTablerosPorOrg(idOrg: Long)
}
