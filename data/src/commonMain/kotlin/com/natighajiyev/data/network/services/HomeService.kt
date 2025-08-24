package com.natighajiyev.data.network.services

import com.natighajiyev.data.network.NetworkConfig.BASE_URL
import com.natighajiyev.domain.model.Response
import com.natighajiyev.domain.network.response.PersonalInfoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class HomeService(private val httpClient: HttpClient) {

    suspend fun getPersonalInfo(): Response<PersonalInfoResponse> {
        return httpClient.get("$BASE_URL/get-personal-info")
            .body<Response<PersonalInfoResponse>>()
    }
}