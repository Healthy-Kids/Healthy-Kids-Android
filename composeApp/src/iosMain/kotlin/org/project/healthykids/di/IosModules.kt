package org.project.healthykids

import app.cash.sqldelight.db.SqlDriver
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }
    single<DailyNewsDatabase> { DailyNewsDatabase(get()) }
}

val iosViewModelModule = module {
    single<ArticleViewModel> { ArticleViewModel(
        getPreviousNewsUseCase = get(),
        getTopNewsUseCase = get()
    ) }
}
