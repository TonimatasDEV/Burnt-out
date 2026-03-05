package dev.wdona.burnt_out.data.dao

import dev.wdona.burnt_out.shared.domain.Usuario

interface UsuarioDao {
    suspend fun getUserById(idUsuario: Long): Usuario
    suspend fun getUsuariosByOrg(idOrg: Long): List<Usuario>
    suspend fun getUsuariosByEquipo(idEquipo: Long): List<Usuario>
    suspend fun getUsuarioByUsername(username: String): Usuario
    suspend fun insertUsuario(usuario: Usuario): Boolean
    suspend fun updateUsuario(usuario: Usuario): Boolean
    suspend fun deleteUsuario(idUsuario: Long): Boolean
    suspend fun updateRiesgoBurnout(idUsuario: Long, riesgo: Double): Boolean
}
