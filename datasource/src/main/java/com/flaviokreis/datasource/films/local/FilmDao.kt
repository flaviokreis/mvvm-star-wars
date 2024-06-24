package com.flaviokreis.datasource.films.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flaviokreis.datasource.films.local.model.FilmEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FilmDao {
    @Query("SELECT * FROM film ORDER BY id")
    fun getAll(): Flow<List<FilmEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(films: List<FilmEntity>)

    @Query("DELETE FROM film")
    suspend fun deleteAll()
}