package com.natighajiyev.domain.repository

import com.natighajiyev.domain.model.Response
import com.natighajiyev.domain.network.response.ChildrenSelectionListResponse
import com.natighajiyev.domain.network.response.ListAllergyResponse
import com.natighajiyev.domain.network.response.ListFileNameResponse

interface ProfileRepository {
    suspend fun getMyChildrenList(): Response<ChildrenSelectionListResponse>

    suspend fun getVaccinesNameList(): Response<List<String>>

    suspend fun getAllergiesNameList(): Response<ListAllergyResponse>

    suspend fun getLastInsertedFiles(): Response<ListFileNameResponse>
}