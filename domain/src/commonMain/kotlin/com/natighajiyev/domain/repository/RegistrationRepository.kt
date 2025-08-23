package com.natighajiyev.domain.repository

import com.natighajiyev.domain.model.CrudModel
import com.natighajiyev.domain.model.Response
import com.natighajiyev.domain.network.request.CreateAccountRequest
import com.natighajiyev.domain.network.request.LoginRequest
import com.natighajiyev.domain.network.response.AccountInfoResponse

interface RegistrationRepository {

    suspend fun getUserInfo(): Response<AccountInfoResponse>

    suspend fun createAccount(request: CreateAccountRequest): Response<CrudModel>

    suspend fun login(request: LoginRequest): Response<CrudModel>

    suspend fun resetPassword(request: String): Response<CrudModel>

    suspend fun checkOtp(request: String): Response<CrudModel>

    suspend fun setNewPassword(request: String): Response<CrudModel>

    suspend fun hasUserViewedWalkthrough(): Boolean

    suspend fun userViewedWalkthrough(viewed: Boolean)
}