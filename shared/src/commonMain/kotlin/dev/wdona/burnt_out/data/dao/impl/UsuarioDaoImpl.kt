package dev.wdona.burnt_out.data.dao.impl

import dev.wdona.burnt_out.data.dao.UsuarioDao
import dev.wdona.burnt_out.data.datasource.mapper.UsuarioMapper
import dev.wdona.burnt_out.shared.db.AppDatabase
import dev.wdona.burnt_out.shared.domain.Usuario

class UsuarioDaoImpl(appDatabase: AppDatabase) : UsuarioDao {
    private val queries = appDatabase.appDatabaseQueries

    override suspend fun getUserById(idUsuario: Long): Usuario {
        val entity = queries.getUserById(idUsuario).executeAsOne()
        // FIXME Esto no es correcto, necesito una query que de el idEquipo de un usuario
        val idEquipo = queries.getUsuariosByEquipo(0).executeAsList()
        // De momento paso 0
        return UsuarioMapper.toDomain(entity, 0L)
    }

    override suspend fun getUsuariosByOrg(idOrg: Long): List<Usuario> {
        return queries.getUsuariosByOrg(idOrg).executeAsList().map {
            UsuarioMapper.toDomain(it, 0L)
        }
    }

    override suspend fun getUsuariosByEquipo(idEquipo: Long): List<Usuario> {
        return queries.getUsuariosByEquipo(idEquipo).executeAsList().map {
            UsuarioMapper.toDomain(it, idEquipo)
        }
    }

    override suspend fun getUsuarioByUsername(username: String): Usuario {
        val entity = queries.getUsuarioByUsername(username).executeAsOne()
        return UsuarioMapper.toDomain(entity, 0L)
    }

    override suspend fun insertUsuario(usuario: Usuario): Boolean {
        return try {
            queries.insertUsuario(
                usuario.idUsuario,
                usuario.username,
                usuario.password,
                usuario.nombre,
                usuario.riesgoBurnout,
                usuario.descripcion,
                usuario.idOrganizacion
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updateUsuario(usuario: Usuario): Boolean {
        return try {
            queries.updateUsuario(
                usuario.username,
                usuario.password,
                usuario.nombre,
                usuario.riesgoBurnout,
                usuario.descripcion,
                usuario.idUsuario
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteUsuario(idUsuario: Long): Boolean {
        return try {
            queries.deleteUsuario(idUsuario)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updateRiesgoBurnout(idUsuario: Long, riesgo: Double): Boolean {
        return try {
            queries.updateRiesgoBurnout(riesgo, idUsuario)
            true
        } catch (e: Exception) {
            false
        }
    }
}
