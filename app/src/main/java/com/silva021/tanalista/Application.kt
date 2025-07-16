package com.silva021.tanalista

import android.app.Application
import com.silva021.tanalista.di.dataStore
import com.silva021.tanalista.di.usecasesModule
import com.silva021.tanalista.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            printLogger()
            modules(
                viewModelModule,
                usecasesModule,
                dataStore
            )
        }
    }
}