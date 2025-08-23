package org.project.healthykids.di

import app.cash.sqldelight.db.SqlDriver
import org.koin.dsl.module
import org.project.healthykids.common.MessageDisplayer

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }
    single<DailyNewsDatabase> { DailyNewsDatabase(get()) }
}

val toastModule = module {
    single { MessageDisplayer() }
}