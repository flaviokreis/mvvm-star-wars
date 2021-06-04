package com.flaviokreis.datasource.films.remote

import com.flaviokreis.datasource.films.model.Film
import kotlinx.coroutines.flow.Flow

internal interface FilmRemoteDatasource {
    suspend fun getFilms(): Flow<List<Film>>
}