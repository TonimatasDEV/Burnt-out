package dev.wdona.burnt_out.data.api

import dev.wdona.burnt_out.shared.domain.Usuario

interface UsuarioApi {
    suspend fun getUserById(idUsuario: Long): Usuario
    suspend fun getUsuariosByOrg(idOrg: Long): List<Usuario>
    suspend fun crearUsuario(usuario: Usuario): Boolean
    suspend fun actualizarUsuario(usuario: Usuario): Boolean
    suspend fun eliminarUsuario(idUsuario: Long): Boolean
    suspend fun login(username: String, contrasena: String): Usuario
}
