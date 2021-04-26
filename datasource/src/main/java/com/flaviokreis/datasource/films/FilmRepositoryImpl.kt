package com.flaviokreis.datasource.films

import com.flaviokreis.datasource.films.model.Film
import com.flaviokreis.datasource.films.remote.FilmRemoteDatasource
import com.flaviokreis.datasource.films.remote.FilmRemoteMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FilmRepositoryImpl(
    private val remoteDatasource: FilmRemoteDatasource,
    private val remoteMapper: FilmRemoteMapper
) : FilmRepository {
    override suspend fun getFilms(): Flow<List<Film>> =
        remoteDatasource.getFilms().map {
            remoteMapper.toModelList(it)
        }
}