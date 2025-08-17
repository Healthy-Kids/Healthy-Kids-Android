package com.natighajiyev.data.network.services

import com.natighajiyev.domain.network.response.AccountInfoResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RegistrationService(private val httpClient: HttpClient) {

    suspend fun getUserInfo(): AccountInfoResponse {
        httpClient.get {  }
    }
}