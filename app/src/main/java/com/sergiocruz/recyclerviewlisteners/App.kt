package com.sergiocruz.recyclerviewlisteners

import android.app.Application
import android.view.LayoutInflater
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val koinModule: Module = module {
            single { LayoutInflater.from(get()) }
            single { PersonAdapter(get()) }
            single { RestaurantAdapter(get()) }
        }

        val koinScopedModule = module {
            scope(named("MY_SCOPE")){
                scoped {  }
                // retrieve Session dependency from current scope
                viewModel { MyViewModel(get()) }
            }

        }

        val koinViewModelModules = module {
            viewModel { (id: String) -> MyViewModel(id) }
            viewModel(named("vm1")) { (id: String) -> MyViewModel(id) }
            viewModel(named("vm2")) { (id: String) -> MyViewModel(id) }
        }

        // start Koin!
        startKoin {

            androidLogger(Level.DEBUG)

            // declare used Android context
            androidContext(this@App)

            // declare modules
            modules(listOf(koinModule, koinScopedModule))
        }

    }

}
