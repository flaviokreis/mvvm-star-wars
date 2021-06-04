package com.flaviokreis.datasource.providers.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flaviokreis.datasource.films.local.FilmDao
import com.flaviokreis.datasource.films.local.model.FilmEntity

@Database(
    entities = [
        FilmEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}