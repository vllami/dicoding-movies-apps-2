package com.dicoding.capstoneakhir

import android.app.Application
import com.dicoding.capstoneakhir.core.di.databaseModule
import com.dicoding.capstoneakhir.core.di.networkModule
import com.dicoding.capstoneakhir.core.di.repositoryModule
import com.dicoding.capstoneakhir.di.useCaseModule
import com.dicoding.capstoneakhir.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CapstoneAkhir : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@CapstoneAkhir)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

}