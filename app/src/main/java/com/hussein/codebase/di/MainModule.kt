package com.hussein.codebase.di

import android.util.Log
import com.hussein.codebase.BuildConfig
import com.hussein.codebase.data.base.TokenManager
import com.hussein.codebase.data.datasource.remote.MainService
import com.hussein.codebase.data.repository.MainRepository
import com.hussein.codebase.domain.usecase.student.GetAllStudentsUseCase
import com.hussein.codebase.domain.usecase.student.GetStudentUseCase
import com.hussein.codebase.presentation.main.MainViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val MainModule =
    module {
        single { ktorHttpClient(get()) }
        single { MainService(get()) }
        single { MainRepository(get()) }
        factory { GetAllStudentsUseCase(get()) }
        factory { GetStudentUseCase(get()) }

        single { TokenManager() }

        viewModelOf(::MainViewModel)
    }

private fun ktorHttpClient(tokenManager: TokenManager) =
    HttpClient(Android) {
        // This will throw exception if response status is not 2xx
        expectSuccess = true

        install(Resources)

        engine {
            connectTimeout = 20_000
            socketTimeout = 20_000
        }

        if (BuildConfig.DEBUG) {
            install(Logging) {
                logger =
                    object : Logger {
                        override fun log(message: String) {
                            Log.d("HTTP Logging", message)
                        }
                    }
                level = LogLevel.ALL
            }
        }
        defaultRequest {
            url("https://freetestapi.com/api/v1/")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header("X-CSRF-TOKEN", "191stLgjevtdLZROykVSb3nLWSLwKGk1eSUE8LJ7")
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                },
            )
        }
    }
