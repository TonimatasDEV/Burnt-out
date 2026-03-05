package dev.wdona.burnt_out.domain.repository

import dev.wdona.burnt_out.shared.domain.Tablero

interface TableroRepository {
    suspend fun getTablerosByOrg(idOrg: Long) : List<Tablero>
    suspend fun getTableroById(idTablero: Long) : Tablero?
    suspend fun crearTablero(tablero: Tablero)
    suspend fun actualizarTablero(tablero: Tablero)
    suspend fun eliminarTablero(idTablero: Long)
}
