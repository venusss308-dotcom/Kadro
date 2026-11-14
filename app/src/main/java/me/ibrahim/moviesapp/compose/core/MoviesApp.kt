package me.ibrahim.moviesapp.compose.core

import android.app.Application
import me.ibrahim.moviesapp.compose.di.coreModule
import me.ibrahim.moviesapp.compose.di.networkModule
import me.ibrahim.moviesapp.compose.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApp)
            androidLogger(level = Level.ERROR)
            modules(repositoryModule, networkModule, coreModule)
        }
    }
}