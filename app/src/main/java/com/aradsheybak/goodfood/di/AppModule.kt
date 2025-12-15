package com.aradsheybak.goodfood.di

import com.aradsheybak.goodfood.data.datastore.PreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { androidContext() }

    single { PreferencesManager(context = get()) }

}