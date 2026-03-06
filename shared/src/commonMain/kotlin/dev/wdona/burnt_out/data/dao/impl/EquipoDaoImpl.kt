package dev.wdona.burnt_out.data.dao.impl

import dev.wdona.burnt_out.data.dao.EquipoDao
import dev.wdona.burnt_out.data.datasource.mapper.EquipoMapper
import dev.wdona.burnt_out.shared.db.AppDatabase
import dev.wdona.burnt_out.shared.domain.Equipo

class EquipoDaoImpl(appDatabase: AppDatabase) : EquipoDao {
    private val queries = appDatabase.appDatabaseQueries

    override suspend fun getEquipoById(idEquipo: Long): Equipo {
        val entity = queries.getEquipoById(idEquipo).executeAsOne()
        return EquipoMapper.toDomain(entity)
    }

    override suspend fun getEquiposByOrg(idOrg: Long): List<Equipo> {
        return queries.getEquiposByOrg(idOrg).executeAsList().map {
            EquipoMapper.toDomain(it)
        }
    }

    override suspend fun insertEquipo(equipo: Equipo): Boolean {
        return try {
            queries.insertEquipo(
                ID_Equipo = equipo.idEquipo,
                Titulo = equipo.titulo,
                Puntuacion = equipo.puntuacion ?: 0,
                FK_ID_Org = equipo.idOrganizacion
            )
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun updateEquipo(equipo: Equipo): Boolean {
        return try {
            queries.updateEquipo(equipo.titulo, equipo.idEquipo)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteEquipo(idEquipo: Long): Boolean {
        return try {
            queries.deleteEquipo(idEquipo)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updatePuntuacion(idEquipo: Long, puntos: Long): Boolean {
        return try {
            queries.updatePuntuacionEquipo(puntos, idEquipo)
            true
        } catch (e: Exception) {
            false
        }
    }
}
