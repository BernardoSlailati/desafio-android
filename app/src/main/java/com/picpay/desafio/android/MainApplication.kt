package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.dataSourceModule
import com.picpay.desafio.android.di.networkModule
import com.picpay.desafio.android.di.repositoryModule
import com.picpay.desafio.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    networkModule,
                    dataSourceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}