package me.ibrahim.moviesapp.compose.di

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.ibrahim.moviesapp.compose.BuildConfig
import me.ibrahim.moviesapp.compose.data.network.MoviesRemoteApi
import me.ibrahim.moviesapp.compose.data.network.MoviesRemoteApiImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


private const val NETWORK_TIME_OUT = 6_000L

val networkModule = module {

    singleOf(::MoviesRemoteApiImpl).bind<MoviesRemoteApi>()

    single {
        HttpClient(Android) {

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor =>", message)
                    }
                }
                level = LogLevel.ALL
            }

            install(HttpTimeout) {
                requestTimeoutMillis = NETWORK_TIME_OUT
                connectTimeoutMillis = NETWORK_TIME_OUT
                socketTimeoutMillis = NETWORK_TIME_OUT
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    useAlternativeNames = true
                    encodeDefaults = false
                })
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header(HttpHeaders.Authorization, "Bearer ${BuildConfig.API_TOKEN}")
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}