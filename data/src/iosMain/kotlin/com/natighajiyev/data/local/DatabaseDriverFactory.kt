package com.natighajiyev.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.project.healthykids.db.HealthyKidsDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            schema = HealthyKidsDatabase.Schema,
            name = "HealthyKidsDatabase"
        )
}