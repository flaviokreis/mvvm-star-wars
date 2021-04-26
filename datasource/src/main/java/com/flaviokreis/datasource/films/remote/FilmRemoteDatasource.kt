package com.flaviokreis.datasource.films.remote

import com.flaviokreis.datasource.films.remote.model.FilmDTO
import kotlinx.coroutines.flow.Flow

internal interface FilmRemoteDatasource {
    suspend fun getFilms(): Flow<List<FilmDTO>>
}