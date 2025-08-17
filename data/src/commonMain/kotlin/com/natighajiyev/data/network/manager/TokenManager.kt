package com.natighajiyev.data.network.manager

import com.natighajiyev.data.db.HealthyKidsDatabase
import com.natighajiyev.data.mapper.mapToToken
import com.natighajiyev.data.mapper.mapToTokenModel
import com.natighajiyev.data.network.services.model.TokenResponse

class TokenManager(
    private val db: HealthyKidsDatabase,
) {

    fun getTokens(): List<TokenResponse>? {
        return db.healthyKidsDatabaseQueries
            .selectToken(::mapToTokenModel)
            .executeAsList()
    }

    fun saveTokens(tokenResponse: TokenResponse) {
//        val token = mapToToken(tokenResponse)
//        return db.healthyKidsDatabaseQueries
//            .insertToken(token)

    }

    fun removeAccessToken() {

    }
}