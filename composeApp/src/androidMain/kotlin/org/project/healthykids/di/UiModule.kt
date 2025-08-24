package org.project.healthykids.di

import androidx.activity.ComponentActivity
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.project.healthykids.common.ActivityProvider
import org.project.healthykids.common.MessageDisplayer
import org.project.healthykids.manager.AndroidFilePicker
import org.project.healthykids.manager.FileManager

val activityModule = module {
    single<ComponentActivity?> { ActivityProvider.get() }
}

val toastModule = module {
    single { MessageDisplayer(androidContext()) }
}

val managerModule = module {
    single<FileManager> { AndroidFilePicker(get()) }
}