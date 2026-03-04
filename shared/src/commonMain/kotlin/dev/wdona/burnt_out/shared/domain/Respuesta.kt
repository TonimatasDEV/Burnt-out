package dev.wdona.burnt_out.shared.domain

import kotlinx.serialization.Serializable

@Serializable
data class Respuesta(val idUsuario: Long, val idPregunta: Long, val anonimo: Boolean, val respuesta: String)
