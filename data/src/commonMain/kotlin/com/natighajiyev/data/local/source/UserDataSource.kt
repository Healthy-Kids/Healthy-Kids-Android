package com.natighajiyev.data.local.source

import com.natighajiyev.data.db.HealthyKidsDatabase

class UserDataSource(
    val db: HealthyKidsDatabase,
) {
    fun saveAsViewed(id: Long = 1L, isViewed: Boolean){
        db.healthyKidsDatabaseQueries
            .saveAsViewed(id, isViewed)
    }

    fun hasUserViewedBefore(): Boolean{
        val result = db.healthyKidsDatabaseQueries
            .hasUserViewedBefore()
            .executeAsList()

        if (result.isEmpty()) return false
        return result.first().isViewed
    }
}