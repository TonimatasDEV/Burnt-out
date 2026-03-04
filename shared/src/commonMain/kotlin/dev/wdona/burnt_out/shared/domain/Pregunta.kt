package dev.wdona.burnt_out.shared.domain

import kotlinx.serialization.Serializable

@Serializable
data class Pregunta(val idPregunta: Long, val pregunta: String, val idOrganizacion: Long)
