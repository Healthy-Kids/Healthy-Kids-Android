package com.natighajiyev.data.di

import app.cash.sqldelight.db.SqlDriver
import com.natighajiyev.data.db.HealthyKidsDatabase
import com.natighajiyev.data.local.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
    single<HealthyKidsDatabase> { HealthyKidsDatabase(get()) }
}