package org.project.healthykids

import org.koin.core.context.startKoin
import org.project.healthykids.di.databaseModule
import org.project.healthykids.di.viewModelsModule
import com.natighajiyev.data.di.sharedModules
import org.project.healthykids.di.toastModule


fun initKoin(){
    startKoin {
        modules(sharedModules + databaseModule + viewModelsModule + toastModule)
    }
}

