package com.natighajiyev.data.network.interceptor

import com.natighajiyev.data.network.manager.TokenManager
import com.natighajiyev.data.network.services.TokenService
import io.ktor.client.plugins.api.Send
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

fun tokenInterceptor(
    service: TokenService,
    manager: TokenManager,
) = createClientPlugin("TokenInterceptor") {

    val mutex = Mutex()

    on(Send) { request ->
        manager.getTokens()?.accessToken?.let { token ->
            request.headers {
                set(HttpHeaders.Authorization, "Bearer $token")
            }
        }

        var result = proceed(request)

        if (result.response.status.value == 401) {
            mutex.withLock {
                val tokens = manager.getTokens()
                val refreshToken = tokens?.refreshToken

                if (!refreshToken.isNullOrEmpty()) {
                    try {
                        val newTokenResponse = service.updateToken(refreshToken)
                        manager.saveTokens(tokens.copy(accessToken = newTokenResponse.accessToken))

                        val newRequest = HttpRequestBuilder().takeFrom(request).apply {
                            headers {
                                set(
                                    HttpHeaders.Authorization,
                                    "Bearer ${newTokenResponse.accessToken}"
                                )
                            }
                        }
                        result = proceed(newRequest)
                    } catch (e: Exception) {
                        manager.removeAccessToken()
                    }
                }
            }
        }

        result
    }
}

