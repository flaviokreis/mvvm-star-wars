package com.flaviokreis.datasource.films.remote

import com.flaviokreis.datasource.films.model.Film
import com.flaviokreis.datasource.films.remote.model.FilmDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class FilmRemoteDatasourceImpl(
    private val filmRemoteService: FilmRemoteService,
    private val remoteMapper: FilmRemoteMapper
) : FilmRemoteDatasource {

    override suspend fun getFilms(): Flow<List<Film>> = flow {
        val response = filmRemoteService.getFilms()
        val list = remoteMapper.toModelList(response.results)

        emit(list)
    }.flowOn(Dispatchers.IO)
}