package dev.wdona.burnt_out.domain.usecase

import dev.wdona.burnt_out.domain.repository.UsuarioRepository

class GetIdUsuarioActual(
    private val usuarioRepository: UsuarioRepository
) {
    var idUsuario: Long? = null

    suspend operator fun invoke(): Long? {

        if (idUsuario == null) {
//            idUsuario = usuarioRepository.getIdUsuarioActual()
        }

        return idUsuario
    }
}
