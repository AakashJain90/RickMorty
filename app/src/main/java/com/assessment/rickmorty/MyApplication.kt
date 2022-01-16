package com.assessment.rickmorty

import android.app.Application
import com.assessment.rickmorty.di.repositoryModule
import com.assessment.rickmorty.di.retrofitModule
import com.assessment.rickmorty.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(viewModelModule, retrofitModule, repositoryModule))
        }
    }
}