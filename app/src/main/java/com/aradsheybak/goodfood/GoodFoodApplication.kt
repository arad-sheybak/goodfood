package com.aradsheybak.goodfood

import android.app.Application
import com.aradsheybak.goodfood.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class GoodFoodApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GoodFoodApplication)
                modules(appModule)
        }
    }
}