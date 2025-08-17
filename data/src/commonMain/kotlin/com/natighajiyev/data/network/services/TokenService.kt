package com.natighajiyev.data.network.services

import com.natighajiyev.data.network.NetworkConfig.BASE_URL
import com.natighajiyev.data.network.services.model.TokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.http.parameters


// install(ContentNegotiation) {
//            json()
//        }
//        install(TokenInterceptor(tokenService, tokenManager))

class TokenService(val httpClient: HttpClient) {

    suspend fun updateToken(refreshToken: String): TokenResponse {
        return httpClient.submitForm(
            url = "$BASE_URL/update-token",
            formParameters = parameters { append("refreshToken", refreshToken) }
        ).body<TokenResponse>()
    }
}