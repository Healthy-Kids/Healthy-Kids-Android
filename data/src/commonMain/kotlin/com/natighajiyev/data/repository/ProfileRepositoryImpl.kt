package com.natighajiyev.data.repository

import com.natighajiyev.data.network.services.ProfileService
import com.natighajiyev.domain.model.Response
import com.natighajiyev.domain.network.response.ChildrenSelectionListResponse
import com.natighajiyev.domain.network.response.ListAllergyResponse
import com.natighajiyev.domain.network.response.ListFileNameResponse
import com.natighajiyev.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val service: ProfileService
): ProfileRepository {
    override suspend fun getMyChildrenList(): Response<ChildrenSelectionListResponse> {
        return service.getMyChildrenList()
    }

    override suspend fun getVaccinesNameList(): Response<List<String>> {
        return service.getVaccinesNameList()
    }

    override suspend fun getAllergiesNameList(): Response<ListAllergyResponse> {
        return service.getAllergiesNameList()
    }

    override suspend fun getLastInsertedFiles(): Response<ListFileNameResponse> {
        return service.getLastInsertedFiles()
    }
}