package com.flaviokreis.datasource.films

import com.flaviokreis.datasource.commons.networkBoundResource
import com.flaviokreis.datasource.films.local.FilmLocalDatasource
import com.flaviokreis.datasource.films.model.Film
import com.flaviokreis.datasource.films.remote.FilmRemoteDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

internal class FilmRepositoryImpl(
    private val localDatasource: FilmLocalDatasource,
    private val remoteDatasource: FilmRemoteDatasource
) : FilmRepository {

    override suspend fun getFilms(): Flow<List<Film>> =
        networkBoundResource(
            fetchFromLocal = {
                localDatasource.getFilms().distinctUntilChanged()
            },
            fetchFromRemote = {
                remoteDatasource.getFilms()
            },
            shouldFetchFromRemote = { content ->
                (content == null || content.isEmpty())
            },
            saveRemoteData = {
                localDatasource.addFilms(it)
            }
        ).flowOn(Dispatchers.IO)

}