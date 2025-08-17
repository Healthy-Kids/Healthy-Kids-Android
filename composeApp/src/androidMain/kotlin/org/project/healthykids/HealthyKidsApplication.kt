package org.project.healthykids

import android.app.Application
import com.natighajiyev.data.di.databaseModule
import com.natighajiyev.data.di.sharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.project.healthykids.di.viewModelsModule

class HealthyKidsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        val modules = sharedModules + viewModelsModule + databaseModule
        startKoin {
            androidContext(this@HealthyKidsApplication)
            modules(modules)
        }
    }
}