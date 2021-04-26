package com.flaviokreis.datasource.films.remote

import com.flaviokreis.datasource.films.remote.model.FilmDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class FilmRemoteDatasourceImpl(
    private val filmRemoteService: FilmRemoteService
) : FilmRemoteDatasource {

    override suspend fun getFilms(): Flow<List<FilmDTO>> = flow {
        val response = filmRemoteService.getFilms()
        emit(response.results)
    }.flowOn(Dispatchers.IO)
}