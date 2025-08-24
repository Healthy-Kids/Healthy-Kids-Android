package com.natighajiyev.domain.repository

import com.natighajiyev.domain.model.Response
import com.natighajiyev.domain.network.response.PersonalInfoResponse

interface HomeRepository {

    suspend fun getPersonalInfo(): Response<PersonalInfoResponse>

}