package dev.wdona.burnt_out.data.api.impl

import dev.wdona.burnt_out.data.api.UsuarioApi
import dev.wdona.burnt_out.shared.domain.Usuario

class UsuarioApiImpl : UsuarioApi {
    override suspend fun getUserById(idUsuario: Long): Usuario {
        return Usuario(idUsuario, "user_remote", "pass", "Nombre Remoto", 0.1, "Desc", 1, 1)
    }

    override suspend fun getUsuariosByOrg(idOrg: Long): List<Usuario> {
        return emptyList()
    }

    override suspend fun crearUsuario(usuario: Usuario): Boolean {
        return true
    }

    override suspend fun actualizarUsuario(usuario: Usuario): Boolean {
        return true
    }

    override suspend fun eliminarUsuario(idUsuario: Long): Boolean {
        return true
    }

    override suspend fun login(username: String, contrasena: String): Usuario {
        return Usuario(1, username, contrasena, "Usuario Logueado", 0.0, "", 1, 1)
    }
}
