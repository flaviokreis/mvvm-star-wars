package com.flaviokreis.mvvmstarwars

import android.app.Application
import com.flaviokreis.datasource.datasourceDI
import com.flaviokreis.mvvmstarwars.movies.moviesDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StarWarsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@StarWarsApplication)
            modules(datasourceDI)
            modules(moviesDI)
        }
    }
}