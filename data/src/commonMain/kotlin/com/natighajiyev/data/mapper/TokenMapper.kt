package com.natighajiyev.data.mapper

import com.natighajiyev.data.db.Token
import com.natighajiyev.data.network.services.model.TokenResponse

fun mapToTokenModel(
    id: Long,
    refreshToken: String,
    accessToken: String?,
): TokenResponse = TokenResponse(id, refreshToken, accessToken)

fun mapToToken(
    tokenResponse: TokenResponse,
): Token = Token(
    id = 1L,
    tokenResponse.refreshToken,
    tokenResponse.accessToken
)