package com.natighajiyev.domain.repository

import com.natighajiyev.domain.model.Response
import com.natighajiyev.domain.network.response.AccountInfoResponse

interface RegistrationRepository {

    suspend fun getUserInfo(): Response<AccountInfoResponse>

    suspend fun hasUserViewedWalkthrough(): Boolean

    suspend fun userViewedWalkthrough(viewed: Boolean)
}