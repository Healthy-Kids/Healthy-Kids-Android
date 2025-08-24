package com.natighajiyev.data.network.services

import com.natighajiyev.data.network.NetworkConfig.BASE_URL
import com.natighajiyev.domain.model.Response
import com.natighajiyev.domain.network.response.ChildrenSelectionListResponse
import com.natighajiyev.domain.network.response.ListAllergyResponse
import com.natighajiyev.domain.network.response.ListFileNameResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProfileService(private val httpClient: HttpClient) {

    suspend fun getMyChildrenList(): Response<ChildrenSelectionListResponse> {
        return httpClient.get("$BASE_URL/get-children-section")
            .body<Response<ChildrenSelectionListResponse>>()
    }

    suspend fun getVaccinesNameList(): Response<List<String>> {
        return httpClient.get("$BASE_URL/get-vaccines-name")
            .body<Response<List<String>>>()
    }

    suspend fun getAllergiesNameList(): Response<ListAllergyResponse> {
        return httpClient.get("$BASE_URL/get-allergies-name")
            .body<Response<ListAllergyResponse>>()
    }

    suspend fun getLastInsertedFiles(): Response<ListFileNameResponse> {
        return httpClient.get("$BASE_URL/get-last-sent-files")
            .body<Response<ListFileNameResponse>>()
    }
}