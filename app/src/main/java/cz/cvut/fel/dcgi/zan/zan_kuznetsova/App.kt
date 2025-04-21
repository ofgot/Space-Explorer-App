package cz.cvut.fel.dcgi.zan.zan_kuznetsova

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.di.appModule
import org.koin.android.ext.koin.androidLogger

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}
