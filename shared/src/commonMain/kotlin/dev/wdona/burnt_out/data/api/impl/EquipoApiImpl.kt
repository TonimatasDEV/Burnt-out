package dev.wdona.burnt_out.data.api.impl

import dev.wdona.burnt_out.data.api.EquipoApi
import dev.wdona.burnt_out.shared.domain.Equipo
import dev.wdona.burnt_out.shared.domain.Usuario

class EquipoApiImpl : EquipoApi {
    override suspend fun getEquipoById(idEquipo: Long): Equipo {
        return Equipo(idEquipo, "Equipo Remoto", 100, 1, emptyList())
    }

    override suspend fun getEquiposByOrg(idOrg: Long): List<Equipo> {
        return emptyList()
    }

    override suspend fun crearEquipo(equipo: Equipo): Boolean {
        return true
    }

    override suspend fun actualizarEquipo(equipo: Equipo): Boolean {
        return true
    }

    override suspend fun eliminarEquipo(idEquipo: Long): Boolean {
        return true
    }

    override suspend fun getMiembrosEquipo(idEquipo: Long): List<Usuario> {
        return emptyList()
    }
}
