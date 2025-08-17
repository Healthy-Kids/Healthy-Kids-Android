package com.natighajiyev.data.mapper

import com.natighajiyev.data.db.Token
import com.natighajiyev.data.network.services.model.TokenResponse

fun mapToTokenModel(
    refreshToken: String,
    accessToken: String?,
): TokenResponse = TokenResponse(refreshToken, accessToken)

fun mapToToken(
    tokenResponse: TokenResponse,
): Token = Token(
    tokenResponse.refreshToken,
    tokenResponse.accessToken
)