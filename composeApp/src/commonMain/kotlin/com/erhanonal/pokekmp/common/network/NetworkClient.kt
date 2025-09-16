package com.erhanonal.pokekmp.common.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkClient(baseUrl: String) {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(DefaultRequest) {
            url(baseUrl)
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = LogLevel.NONE
        }
    }
}
