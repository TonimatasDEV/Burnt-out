package dev.wdona.burnt_out.domain.repository

import dev.wdona.burnt_out.shared.domain.Usuario

interface UsuarioRepository {
    suspend fun getUserById(idUsuario: Long): Usuario
    suspend fun getUsuariosByOrg(idOrg: Long): List<Usuario>
    suspend fun getUsuariosByEquipo(idEquipo: Long): List<Usuario>
    suspend fun crearUsuario(usuario: Usuario)
    suspend fun actualizarUsuario(usuario: Usuario)
    suspend fun eliminarUsuario(idUsuario: Long)
    suspend fun updateRiesgoBurnout(idUsuario: Long, riesgo: Double)
    suspend fun login(username: String, contrasena: String): Usuario
}
