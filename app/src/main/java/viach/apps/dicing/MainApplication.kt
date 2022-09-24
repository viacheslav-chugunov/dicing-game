package viach.apps.dicing

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import viach.apps.dicing.di.aiModule
import viach.apps.dicing.di.dicingModule

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                dicingModule,
                aiModule
            )
        }
    }

}