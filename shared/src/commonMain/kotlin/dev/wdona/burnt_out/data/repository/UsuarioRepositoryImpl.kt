package dev.wdona.burnt_out.data.repository

import dev.wdona.burnt_out.data.datasource.local.OperacionPendienteLocalDataSource
import dev.wdona.burnt_out.data.datasource.local.UsuarioLocalDataSource
import dev.wdona.burnt_out.data.datasource.mapper.UsuarioMapper
import dev.wdona.burnt_out.data.datasource.remote.UsuarioRemoteDataSource
import dev.wdona.burnt_out.domain.entity.Entity
import dev.wdona.burnt_out.domain.model.TipoAccion
import dev.wdona.burnt_out.domain.repository.UsuarioRepository
import dev.wdona.burnt_out.shared.domain.Usuario

class UsuarioRepositoryImpl(
    private val local: UsuarioLocalDataSource,
    private val remote: UsuarioRemoteDataSource,
    private val pendiente: OperacionPendienteLocalDataSource
) : UsuarioRepository {

    override suspend fun getUserById(idUsuario: Long): Usuario {
        try {
            val usuario = remote.getUserById(idUsuario)
            local.eliminarUsuario(usuario.idUsuario)
            local.crearUsuario(usuario)
        } catch (e: Exception) {
            println("Error al obtener usuario del servidor: ${e.message}")
        }
        return local.getUserById(idUsuario)
    }

    override suspend fun getUsuariosByOrg(idOrg: Long): List<Usuario> {
        try {
            val usuarios = remote.getUsuariosByOrg(idOrg)
            local.eliminarUsuariosPorOrg(idOrg)
            usuarios.forEach { local.crearUsuario(it) }
        } catch (e: Exception) {
            println("Error al obtener usuarios del servidor: ${e.message}")
        }
        return local.getUsuariosByOrg(idOrg)
    }

    override suspend fun getUsuariosByEquipo(idEquipo: Long): List<Usuario> {
        // FIXME FALTA SINCRONIZAR REMOTE
        return local.getUsuariosByEquipo(idEquipo)
    }

    override suspend fun crearUsuario(usuario: Usuario) {
        try {
            local.crearUsuario(usuario)
        } catch (e: Exception) {
            println("Error al crear usuario localmente: ${e.message}")
        }

        var exito = false
        try {
            exito = remote.crearUsuario(usuario)
        } catch (e: Exception) {
            println("Error al crear usuario en el servidor: ${e.message}")
        }

        try {
            pendiente.insertOperacionPendiente(
                TipoAccion.CREACION.getNombreAccion(),
                Entity.USUARIO.getNombreEntity(),
                usuario.idUsuario,
                UsuarioMapper.toJson(usuario),
                System.currentTimeMillis(),
                if (exito) 1L else 0L
            )
        } catch (e: Exception) {
            println("Error al registrar operación pendiente: ${e.message}")
        }
    }

    override suspend fun actualizarUsuario(usuario: Usuario) {
        try {
            local.actualizarUsuario(usuario)
        } catch (e: Exception) {
            println("Error al actualizar usuario localmente: ${e.message}")
        }

        var exito = false
        try {
            exito = remote.actualizarUsuario(usuario)
        } catch (e: Exception) {
            println("Error al actualizar usuario en el servidor: ${e.message}")
        }

        try {
            pendiente.insertOperacionPendiente(
                TipoAccion.ACTUALIZACION.getNombreAccion(),
                Entity.USUARIO.getNombreEntity(),
                usuario.idUsuario,
                UsuarioMapper.toJson(usuario),
                System.currentTimeMillis(),
                if (exito) 1L else 0L
            )
        } catch (e: Exception) {
            println("Error al registrar operación pendiente: ${e.message}")
        }
    }

    override suspend fun eliminarUsuario(idUsuario: Long) {
        try {
            local.eliminarUsuario(idUsuario)
        } catch (e: Exception) {
            println("Error al eliminar usuario localmente: ${e.message}")
        }

        var exito = false
        try {
            exito = remote.eliminarUsuario(idUsuario)
        } catch (e: Exception) {
            println("Error al eliminar usuario en el servidor: ${e.message}")
        }

        try {
            pendiente.insertOperacionPendiente(
                TipoAccion.ELIMINACION.getNombreAccion(),
                Entity.USUARIO.getNombreEntity(),
                idUsuario,
                "",
                System.currentTimeMillis(),
                if (exito) 1L else 0L
            )
        } catch (e: Exception) {
            println("Error al registrar operación pendiente: ${e.message}")
        }
    }

    override suspend fun updateRiesgoBurnout(idUsuario: Long, riesgo: Double) {
        try {
            // TODO: Actualizar en el servidor??
            local.updateRiesgoBurnout(idUsuario, riesgo)
        } catch (e: Exception) {
            println("Error al actualizar riesgo: ${e.message}")
        }
    }

    override suspend fun login(username: String, contrasena: String): Usuario {
        return remote.login(username, contrasena).also {
            local.crearUsuario(it)
        }
    }
}
