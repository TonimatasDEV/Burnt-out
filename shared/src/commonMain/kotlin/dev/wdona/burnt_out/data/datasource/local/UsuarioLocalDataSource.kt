package dev.wdona.burnt_out.data.datasource.local

import dev.wdona.burnt_out.shared.domain.Usuario

interface UsuarioLocalDataSource {
    suspend fun getUserById(idUsuario: Long): Usuario
    suspend fun getUsuariosByOrg(idOrg: Long): List<Usuario>
    suspend fun getUsuariosByEquipo(idEquipo: Long): List<Usuario>
    suspend fun crearUsuario(usuario: Usuario): Boolean
    suspend fun actualizarUsuario(usuario: Usuario): Boolean
    suspend fun eliminarUsuario(idUsuario: Long): Boolean
    suspend fun updateRiesgoBurnout(idUsuario: Long, riesgo: Double): Boolean
    suspend fun eliminarUsuariosPorOrg(idOrg: Long)
}
