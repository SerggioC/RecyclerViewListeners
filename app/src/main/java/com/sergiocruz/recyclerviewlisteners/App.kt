package com.sergiocruz.recyclerviewlisteners

import android.app.Application
import android.view.LayoutInflater
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val koinModule: Module = module {
            single { LayoutInflater.from(get()) }
            single { PersonAdapter(get()) }
            single { RestaurantAdapter(get()) }
        }

        // start Koin!
        startKoin {

            androidLogger(Level.DEBUG)

            // declare used Android context
            androidContext(this@App)

            // declare modules
            modules(koinModule)
        }

    }

}
