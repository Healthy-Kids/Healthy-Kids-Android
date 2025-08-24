package org.project.healthykids.di

import app.cash.sqldelight.db.SqlDriver
import org.koin.dsl.module
import org.project.healthykids.common.MessageDisplayer
import org.project.healthykids.manager.IOSFilePicker
import org.project.healthykids.manager.getFilePicker

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }
    single<DailyNewsDatabase> { DailyNewsDatabase(get()) }
}

val toastModule = module {
    single { MessageDisplayer() }
}

val managerModule = module {
    single { IOSFilePicker(null) }
}