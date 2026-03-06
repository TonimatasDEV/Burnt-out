package dev.wdona.burnt_out.data.dao

import dev.wdona.burnt_out.shared.domain.Usuario

interface UsuarioDao {
    suspend fun getUserById(idUsuario: Long): Usuario
    suspend fun getUsuariosByOrg(idOrg: Long): List<Usuario>
    suspend fun getUsuariosByEquipo(idEquipo: Long): List<Usuario>
    suspend fun getUsuarioByUsername(username: String): Usuario
    suspend fun crearUsuario(usuario: Usuario): Long
    suspend fun actualizarUsuario(usuario: Usuario): Boolean
    suspend fun eliminarUsuario(idUsuario: Long): Boolean
    suspend fun insertOrUpdateUsuario(usuario: Usuario): Boolean
    suspend fun updateRiesgoBurnout(idUsuario: Long, riesgo: Double): Boolean
}
