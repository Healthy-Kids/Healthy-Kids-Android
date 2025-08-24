package com.natighajiyev.data.repository

import com.natighajiyev.data.network.services.HomeService
import com.natighajiyev.domain.model.Response
import com.natighajiyev.domain.network.response.PersonalInfoResponse
import com.natighajiyev.domain.repository.HomeRepository

class HomeRepositoryImpl(private val service: HomeService): HomeRepository {

    override suspend fun getPersonalInfo(): Response<PersonalInfoResponse> {
        return service.getPersonalInfo()
    }
}