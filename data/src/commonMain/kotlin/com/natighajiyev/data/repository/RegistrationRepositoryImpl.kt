package com.natighajiyev.data.repository

import com.natighajiyev.data.local.source.UserDataSource
import com.natighajiyev.data.network.services.RegistrationService
import com.natighajiyev.domain.model.Response
import com.natighajiyev.domain.network.response.AccountInfoResponse
import com.natighajiyev.domain.repository.RegistrationRepository

class RegistrationRepositoryImpl(
    private val service: RegistrationService,
    private val source: UserDataSource
): RegistrationRepository {
    override suspend fun getUserInfo(): Response<AccountInfoResponse> {
        return service.getUserInfo()
    }

    override suspend fun hasUserViewedWalkthrough(): Boolean {
        return source.hasUserViewedBefore()
    }

    override suspend fun userViewedWalkthrough(viewed: Boolean) {
        source.saveAsViewed(isViewed = viewed)
    }
}