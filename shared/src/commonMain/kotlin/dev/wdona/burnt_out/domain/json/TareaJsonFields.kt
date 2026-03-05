package dev.wdona.burnt_out.domain.json

enum class TareaJsonFields(nombreCampo: String) {
    ID("idTarea"),
    NOMBRE("titulo"),
    DESCRIPCION("descripcion"),
    ESTADO("estado"),
    ID_TABLERO("idTableroPerteneciente"),
    ID_USUARIO_ASIGNADO("idUsuarioAsignado"),
    ID_SUBTAREAS("idSubtareas")
}