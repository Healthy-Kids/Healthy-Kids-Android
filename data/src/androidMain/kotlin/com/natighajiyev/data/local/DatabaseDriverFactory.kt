package com.natighajiyev.data.local

import app.cash.sqldelight.db.SqlDriver
import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.natighajiyev.data.db.HealthyKidsDatabase

actual class DatabaseDriverFactory(private val androidContext: Context) {

    actual fun createDriver(): SqlDriver{
        return AndroidSqliteDriver(
            schema = HealthyKidsDatabase.Schema,
            context = androidContext,
            name = "HealthyKidsDatabase"
        )
    }
}