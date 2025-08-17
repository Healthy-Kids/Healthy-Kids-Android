package com.natighajiyev.data.network.manager

import com.natighajiyev.data.db.HealthyKidsDatabase
import com.natighajiyev.data.mapper.mapToToken
import com.natighajiyev.data.mapper.mapToTokenModel
import com.natighajiyev.data.network.services.model.TokenResponse

class TokenManager(
    private val db: HealthyKidsDatabase,
) {

    fun getTokens(): TokenResponse? {
        val tokens = db.healthyKidsDatabaseQueries
            .selectToken(::mapToTokenModel)
            .executeAsList()

        if (tokens.isEmpty()) return null

        return tokens.first()
    }

    fun saveTokens(tokenResponse: TokenResponse) {
        val token = mapToToken(tokenResponse)
        db.healthyKidsDatabaseQueries
            .insertToken(token.refreshToken, token.accessToken)
    }

    fun removeAccessToken() {
        db.healthyKidsDatabaseQueries.clearToken()
    }
}