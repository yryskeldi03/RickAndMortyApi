package kg.geek.rickmortyapi

import android.app.Application
import kg.geek.rickmortyapi.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(koinModules)
        }
    }
}