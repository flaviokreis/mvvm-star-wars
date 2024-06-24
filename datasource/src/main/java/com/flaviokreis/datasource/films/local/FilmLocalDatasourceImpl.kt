package com.flaviokreis.datasource.films.local

import com.flaviokreis.datasource.films.model.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FilmLocalDatasourceImpl(
    private val filmDao: FilmDao,
    private val filmLocalMapper: FilmLocalMapper
) : FilmLocalDatasource {
    override suspend fun getFilms(): Flow<List<Film>> =
        filmDao.getAll().map { filmLocalMapper.toModelList(it) }

    override suspend fun addFilms(list: List<Film>) {
        val entityList = list.map {
            filmLocalMapper.toEntity(it)
        }
        filmDao.insertAll(entityList)
    }

    override suspend fun removeAll() {
        filmDao.deleteAll()
    }

}