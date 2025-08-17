package org.project.healthykids

import android.app.Application
import com.natighajiyev.data.di.databaseModule
import com.natighajiyev.data.di.localModules
import com.natighajiyev.data.di.networkModules
import com.natighajiyev.data.di.repository

class HealthyKidsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(){
        val modules = sharedModules + androidViewModelModule + databaseModule
//        startKoin {
//            androidContext(this@DailyNewsApplication)
//            modules(modules)
//        }
    }
}