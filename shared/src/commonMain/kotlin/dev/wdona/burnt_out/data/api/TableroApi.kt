package dev.wdona.burnt_out.data.api

import dev.wdona.burnt_out.shared.domain.Tablero
import io.ktor.client.statement.HttpResponse

interface TableroApi {
    suspend fun getTableroById(idTablero: Long): Tablero
    suspend fun getTablerosByOrg(idOrg: Long): List<Tablero>
    suspend fun crearTablero(tablero: Tablero): HttpResponse
    suspend fun actualizarTablero(tablero: Tablero): HttpResponse
    suspend fun eliminarTablero(idTablero: Long): HttpResponse
}
