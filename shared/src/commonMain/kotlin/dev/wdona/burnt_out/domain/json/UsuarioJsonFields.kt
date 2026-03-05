package dev.wdona.burnt_out.domain.json

enum class UsuarioJsonFields(val nombreCampo: String) {
    ID("idUsuario"),
    USERNAME("username"),
    CONTRASENA("contrasena"),
    NOMBRE("nombre"),
    RIESGO_BURNOUT("riesgoBurnout"),
    DESCRIPCION("descripcion"),
    ID_ORGANIZACION("idOrganizacion")
}
