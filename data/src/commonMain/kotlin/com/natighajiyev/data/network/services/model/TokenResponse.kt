package com.natighajiyev.data.network.services.model

data class TokenResponse(
    private val id: Long = 1L,
    val refreshToken: String,
    val accessToken: String?,
)
