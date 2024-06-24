package com.flaviokreis.datasource.films.local

import com.flaviokreis.datasource.films.model.Film
import kotlinx.coroutines.flow.Flow

internal interface FilmLocalDatasource {
    suspend fun getFilms(): Flow<List<Film>>
    suspend fun addFilms(list: List<Film>)
    suspend fun removeAll()
}