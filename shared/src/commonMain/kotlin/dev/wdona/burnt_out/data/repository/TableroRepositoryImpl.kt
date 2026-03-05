package dev.wdona.burnt_out.data.repository

import dev.wdona.burnt_out.data.datasource.local.OperacionPendienteLocalDataSource
import dev.wdona.burnt_out.data.datasource.local.TableroLocalDataSource
import dev.wdona.burnt_out.data.datasource.mapper.TableroMapper
import dev.wdona.burnt_out.data.datasource.remote.TableroRemoteDataSource
import dev.wdona.burnt_out.domain.entity.Entity
import dev.wdona.burnt_out.domain.model.TipoAccion
import dev.wdona.burnt_out.domain.repository.TableroRepository
import dev.wdona.burnt_out.shared.domain.Tablero

class TableroRepositoryImpl(
    private val local: TableroLocalDataSource,
    private val remote: TableroRemoteDataSource,
    private val pendiente: OperacionPendienteLocalDataSource
) : TableroRepository {

    override suspend fun getTablerosByOrg(idOrg: Long): List<Tablero> {
        try {
            val tableros = remote.getTablerosByOrg(idOrg)
            local.eliminarTablerosPorOrg(idOrg)
            tableros.forEach { local.crearTablero(it) }
        } catch (e: Exception) {
            println("Error al obtener tableros del servidor: ${e.message}")
        }
        return local.getTablerosByOrg(idOrg)
    }

    override suspend fun getTableroById(idTablero: Long): Tablero? {
        try {
            val tablero = remote.getTableroById(idTablero)
            local.eliminarTablero(tablero.idTablero)
            local.crearTablero(tablero)
        } catch (e: Exception) {
            println("Error al obtener tablero del servidor: ${e.message}")
        }
        return local.getTableroById(idTablero)
    }

    override suspend fun crearTablero(tablero: Tablero) {
        try {
            local.crearTablero(tablero)
        } catch (e: Exception) {
            println("Error al crear tablero localmente: ${e.message}")
        }

        var exito = false
        try {
            exito = remote.crearTablero(tablero)
        } catch (e: Exception) {
            println("Error al crear tablero en el servidor: ${e.message}")
        }

        try {
            pendiente.insertOperacionPendiente(
                TipoAccion.CREACION.getNombreAccion(),
                Entity.TABLERO.getNombreEntity(),
                tablero.idTablero,
                TableroMapper.toJson(tablero),
                System.currentTimeMillis(),
                if (exito) 1L else 0L
            )
        } catch (e: Exception) {
            println("Error al registrar operación pendiente: ${e.message}")
        }
    }

    override suspend fun actualizarTablero(tablero: Tablero) {
        try {
            local.actualizarTablero(tablero)
        } catch (e: Exception) {
            println("Error al actualizar tablero localmente: ${e.message}")
        }

        var exito = false
        try {
            exito = remote.actualizarTablero(tablero)
        } catch (e: Exception) {
            println("Error al actualizar tablero en el servidor: ${e.message}")
        }

        try {
            pendiente.insertOperacionPendiente(
                TipoAccion.ACTUALIZACION.getNombreAccion(),
                Entity.TABLERO.getNombreEntity(),
                tablero.idTablero,
                TableroMapper.toJson(tablero),
                System.currentTimeMillis(),
                if (exito) 1L else 0L
            )
        } catch (e: Exception) {
            println("Error al registrar operación pendiente: ${e.message}")
        }
    }

    override suspend fun eliminarTablero(idTablero: Long) {
        try {
            local.eliminarTablero(idTablero)
        } catch (e: Exception) {
            println("Error al eliminar tablero localmente: ${e.message}")
        }

        var exito = false
        try {
            exito = remote.eliminarTablero(idTablero)
        } catch (e: Exception) {
            println("Error al eliminar tablero en el servidor: ${e.message}")
        }

        try {
            pendiente.insertOperacionPendiente(
                TipoAccion.ELIMINACION.getNombreAccion(),
                Entity.TABLERO.getNombreEntity(),
                idTablero,
                "",
                System.currentTimeMillis(),
                if (exito) 1L else 0L
            )
        } catch (e: Exception) {
            println("Error al registrar operación pendiente: ${e.message}")
        }
    }
}
