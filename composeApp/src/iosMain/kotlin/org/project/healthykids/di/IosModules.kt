package org.project.healthykids.di

import app.cash.sqldelight.db.SqlDriver
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }
    single<DailyNewsDatabase> { DailyNewsDatabase(get()) }
}
