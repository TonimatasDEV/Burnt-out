package dev.wdona.burnt_out.data.datasource.local.impl

import dev.wdona.burnt_out.data.dao.UsuarioDao
import dev.wdona.burnt_out.data.datasource.local.UsuarioLocalDataSource
import dev.wdona.burnt_out.shared.domain.Usuario

class UsuarioLocalDataSourceImpl(private val usuarioDao: UsuarioDao) : UsuarioLocalDataSource {
    override suspend fun getUserById(idUsuario: Long): Usuario {
        return usuarioDao.getUserById(idUsuario)
    }

    override suspend fun getUsuariosByOrg(idOrg: Long): List<Usuario> {
        return usuarioDao.getUsuariosByOrg(idOrg)
    }

    override suspend fun getUsuariosByEquipo(idEquipo: Long): List<Usuario> {
        return usuarioDao.getUsuariosByEquipo(idEquipo)
    }

    override suspend fun crearUsuario(usuario: Usuario): Boolean {
        return usuarioDao.insertUsuario(usuario)
    }

    override suspend fun actualizarUsuario(usuario: Usuario): Boolean {
        return usuarioDao.updateUsuario(usuario)
    }

    override suspend fun eliminarUsuario(idUsuario: Long): Boolean {
        return usuarioDao.deleteUsuario(idUsuario)
    }

    override suspend fun updateRiesgoBurnout(idUsuario: Long, riesgo: Double): Boolean {
        return usuarioDao.updateRiesgoBurnout(idUsuario, riesgo)
    }

    override suspend fun eliminarUsuariosPorOrg(idOrg: Long) {
        val usuarios = usuarioDao.getUsuariosByOrg(idOrg)
        usuarios.forEach { usuarioDao.deleteUsuario(it.idUsuario) }
    }
}
