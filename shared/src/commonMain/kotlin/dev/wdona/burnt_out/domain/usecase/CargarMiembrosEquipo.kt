package dev.wdona.burnt_out.domain.usecase

import dev.wdona.burnt_out.domain.repository.UsuarioRepository
import dev.wdona.burnt_out.shared.domain.Usuario

class CargarMiembrosEquipo(
    private val usuarioRepository: UsuarioRepository
) {
    suspend operator fun invoke(idEquipo: Long) : List<Usuario> {
        return usuarioRepository.getUsuariosByEquipo(idEquipo)
    }
}