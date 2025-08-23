package org.project.healthykids.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.project.healthykids.common.MessageDisplayer

val toastModule = module {
    single { MessageDisplayer(androidContext()) }
}