package com.natighajiyev.data.network.services.model

data class TokenResponse(
    val refreshToken: String,
    val accessToken: String?,
)
