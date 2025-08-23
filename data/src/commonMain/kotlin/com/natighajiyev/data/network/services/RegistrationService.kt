package com.natighajiyev.data.network.services

import com.natighajiyev.data.network.NetworkConfig.BASE_URL
import com.natighajiyev.domain.model.CrudModel
import com.natighajiyev.domain.model.Response
import com.natighajiyev.domain.network.request.CreateAccountRequest
import com.natighajiyev.domain.network.request.LoginRequest
import com.natighajiyev.domain.network.response.AccountInfoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RegistrationService(private val httpClient: HttpClient) {
    // TODO: Create Repository, RepositoryImpl, UseCases, ViewModel Implementations
    suspend fun getUserInfo(): Response<AccountInfoResponse> {
        return httpClient
            .get("$BASE_URL/get-user-info")
            .body<Response<AccountInfoResponse>>()
    }

    suspend fun createAccount(request: CreateAccountRequest): Response<CrudModel> {
        return httpClient
            .post("$BASE_URL/create-account") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            .body<Response<CrudModel>>()
    }

    suspend fun login(request: LoginRequest): Response<CrudModel> {
        return httpClient
            .post("$BASE_URL/sign-in") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            .body<Response<CrudModel>>()
    }

    suspend fun resetPassword(request: String): Response<CrudModel> {
        return httpClient
            .post("$BASE_URL/reset-password") {
                setBody(request)
            }
            .body<Response<CrudModel>>()
    }

    suspend fun checkOtp(request: String): Response<CrudModel> {
        return httpClient
            .post("$BASE_URL/otp") { setBody(request) }
            .body<Response<CrudModel>>()
    }

    suspend fun setNewPassword(request: String): Response<CrudModel> {
        return httpClient
            .post("$BASE_URL/set-new-password") { setBody(request) }
            .body<Response<CrudModel>>()
    }
}