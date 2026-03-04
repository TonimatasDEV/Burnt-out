package dev.wdona.burnt_out.shared.domain

import kotlinx.serialization.Serializable

@Serializable
data class Organizacion(val idOrganizacion: Long, val nombre: String)
