package dev.wdona.burnt_out.shared.network

import dev.wdona.burnt_out.API_PATH
import dev.wdona.burnt_out.SERVER_PORT
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object ApiClient {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }

        defaultRequest {
            url("http://$HOST:$SERVER_PORT/$API_PATH/")
            contentType(ContentType.Application.Json)
        }
    }
}