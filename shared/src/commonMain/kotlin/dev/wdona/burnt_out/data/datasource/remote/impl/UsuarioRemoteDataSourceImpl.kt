package dev.wdona.burnt_out.data.datasource.remote.impl

import dev.wdona.burnt_out.data.api.UsuarioApi
import dev.wdona.burnt_out.data.datasource.remote.UsuarioRemoteDataSource
import dev.wdona.burnt_out.shared.domain.Usuario

class UsuarioRemoteDataSourceImpl(private val usuarioApi: UsuarioApi) : UsuarioRemoteDataSource {
    override suspend fun getUserById(idUsuario: Long): Usuario {
        return usuarioApi.getUserById(idUsuario)
    }

    override suspend fun getUsuariosByOrg(idOrg: Long): List<Usuario> {
        return usuarioApi.getUsuariosByOrg(idOrg)
    }

    override suspend fun crearUsuario(usuario: Usuario): Boolean {
        return usuarioApi.crearUsuario(usuario)
    }

    override suspend fun actualizarUsuario(usuario: Usuario): Boolean {
        return usuarioApi.actualizarUsuario(usuario)
    }

    override suspend fun eliminarUsuario(idUsuario: Long): Boolean {
        return usuarioApi.eliminarUsuario(idUsuario)
    }

    override suspend fun login(username: String, contrasena: String): Usuario {
        return usuarioApi.login(username, contrasena)
    }
}
