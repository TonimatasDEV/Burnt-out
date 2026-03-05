package dev.wdona.burnt_out.data.datasource.local

import dev.wdona.burnt_out.data.datasource.common.TareaDataSource

interface TareaLocalDataSource : TareaDataSource {
    suspend fun eliminarTareasPorTablero(idTablero: Long)
}