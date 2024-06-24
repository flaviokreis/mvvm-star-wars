package com.flaviokreis.datasource.di

import androidx.room.Room
import com.flaviokreis.datasource.providers.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val databaseDI = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "starwars-database"
        ).build()
    }

    factory { get<AppDatabase>().filmDao() }
}