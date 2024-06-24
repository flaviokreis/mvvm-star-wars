package com.flaviokreis.datasource.films

import com.flaviokreis.datasource.films.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    fun getFilms(): Flow<List<Film>>
}