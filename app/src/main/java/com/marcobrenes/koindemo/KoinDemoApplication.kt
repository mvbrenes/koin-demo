package com.marcobrenes.koindemo

import android.app.Application
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class KoinDemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin(
            this,
            listOf(applicationModule, browseModule),
            loadPropertiesFromFile = true,
            logger = KoinLogger()
        )
    }

}