package dev.wdona.burnt_out.domain.model

/*
    ID_Accion INTEGER NOT NULL,
    tipo_accion TEXT NOT NULL,
    tabla_afectada TEXT NOT NULL,
    id_afectado INTEGER NOT NULL,
    datos_json TEXT NOT NULL,
    timestamp_creacion INTEGER NOT NULL,
    sincronizado INTEGER DEFAULT 0,
    PRIMARY KEY (ID_Accion)
 */

data class OperacionPendiente(
    val idAccion: Long,
    val tipoAccion: String,
    val tablaAfectada: String,
    val idAfectado: Int,
    val datosJson: String,
    val timestampCreacion: Long,
    val sincronizado: Boolean
)