package com.natighajiyev.data.network.services

import com.natighajiyev.data.network.NetworkConfig.BASE_URL
import com.natighajiyev.domain.model.Response
import com.natighajiyev.domain.network.response.AccountInfoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RegistrationService(private val httpClient: HttpClient) {
    // TODO: Create Repository, RepositoryImpl, UseCases, ViewModel Implementations
    suspend fun getUserInfo(): Response<AccountInfoResponse> {
        return httpClient
            .get("$BASE_URL/get-user-info")
            .body<Response<AccountInfoResponse>>()
    }
}