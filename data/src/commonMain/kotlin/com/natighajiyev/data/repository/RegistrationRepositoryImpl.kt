package com.natighajiyev.data.repository

import com.natighajiyev.data.local.source.UserDataSource
import com.natighajiyev.data.network.services.RegistrationService
import com.natighajiyev.domain.model.CrudModel
import com.natighajiyev.domain.model.Response
import com.natighajiyev.domain.network.request.CreateAccountRequest
import com.natighajiyev.domain.network.request.LoginRequest
import com.natighajiyev.domain.network.response.AccountInfoResponse
import com.natighajiyev.domain.repository.RegistrationRepository

class RegistrationRepositoryImpl(
    private val service: RegistrationService,
    private val source: UserDataSource
): RegistrationRepository {
    override suspend fun getUserInfo(): Response<AccountInfoResponse> {
        return service.getUserInfo()
    }

    override suspend fun createAccount(request: CreateAccountRequest): Response<CrudModel> {
        return service.createAccount(request)
    }

    override suspend fun login(request: LoginRequest): Response<CrudModel> {
        return service.login(request)
    }

    override suspend fun resetPassword(request: String): Response<CrudModel> {
        return service.resetPassword(request)
    }

    override suspend fun checkOtp(request: String): Response<CrudModel> {
        return service.checkOtp(request)
    }

    override suspend fun setNewPassword(request: String): Response<CrudModel> {
        return service.setNewPassword(request)
    }

    override suspend fun hasUserViewedWalkthrough(): Boolean {
        return source.hasUserViewedBefore()
    }

    override suspend fun userViewedWalkthrough(viewed: Boolean) {
        source.saveAsViewed(isViewed = viewed)
    }
}