package dev.wdona.burnt_out.shared.utils

import com.russhwolf.settings.Settings

class SettingsManager(private val settings: Settings = Settings()) {

    companion object {
        private const val KEY_PRIMERA_EJECUCION = "es_primera_ejecucion"
        private const val KEY_DARK_MODE = "dark_mode"
        private const val KEY_ID_USUARIO_ACTUAL = "id_usuario_actual"
        private const val KEY_TOKEN_USUARIO = "token_usuario"

    }

    fun setIdUsuarioActual(id: Long?) {
        settings.putLong(KEY_ID_USUARIO_ACTUAL, id ?: Long.MIN_VALUE)
    }

    fun getIdUsuarioActual(): Long? {
        val id = settings.getLong(KEY_ID_USUARIO_ACTUAL, Long.MIN_VALUE)
        return if (id == Long.MIN_VALUE) null else id
    }

    fun setTokenUsuario(token: String?) {
        settings.putString(KEY_TOKEN_USUARIO, token ?: "")
    }

    fun getTokenUsuario(): String? {
        val token = settings.getString(KEY_TOKEN_USUARIO, "")
        return token.ifEmpty { null }
    }

    fun setPrimeraEjecucion(primeraEjecucion: Boolean) {
        settings.putBoolean(KEY_PRIMERA_EJECUCION, primeraEjecucion)
    }

    fun getPrimeraEjecucion(): Boolean {
        return settings.getBoolean(KEY_PRIMERA_EJECUCION, true)
    }


}