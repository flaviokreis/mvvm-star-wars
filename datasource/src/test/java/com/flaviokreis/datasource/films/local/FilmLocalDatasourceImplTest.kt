package com.flaviokreis.datasource.films.local

import io.mockk.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class FilmLocalDatasourceImplTest {

    @Test
    fun `GIVEN database with films WHEN getFilms is called THEN returns all films`() =
        runBlocking {
            // GIVEN
            val filmDao: FilmDao = mockk()
            val filmMapper: FilmLocalMapper = mockk()
            val localDatasource = FilmLocalDatasourceImpl(filmDao, filmMapper)

            coEvery { filmDao.getAll() } returns flow {
                emit(listOf(mockk()))
            }

            every { filmMapper.toModelList(any()) } returns listOf(mockk())

            // WHEN
            val result = localDatasource.getFilms().first()

            // THEN
            assertTrue(result.isNotEmpty())
            coVerifySequence {
                filmDao.getAll()
                filmMapper.toModelList(any())
            }
        }

    @Test
    fun `GIVEN list of films WHEN addFilms is called THEN all films is inserted into database`() =
        runBlocking {
            // GIVEN
            val filmDao: FilmDao = mockk()
            val filmMapper: FilmLocalMapper = mockk()
            val localDatasource = FilmLocalDatasourceImpl(filmDao, filmMapper)

            every { filmMapper.toEntity(any()) } returns mockk()

            coEvery { filmDao.insertAll(any()) } just Runs

            // WHEN
            localDatasource.addFilms(listOf(mockk()))

            // THEN
            coVerifySequence {
                filmMapper.toEntity(any())
                filmDao.insertAll(any())
            }
        }

    @Test
    fun `GIVEN film database WHEN removeAll is called THEN database deleteAll method is called`() =
        runBlocking {
            // GIVEN
            val filmDao: FilmDao = mockk()
            val filmMapper: FilmLocalMapper = mockk()
            val localDatasource = FilmLocalDatasourceImpl(filmDao, filmMapper)

            coEvery { filmDao.deleteAll() } just Runs

            // WHEN
            localDatasource.removeAll()

            // THEN
            coVerifySequence {
                filmDao.deleteAll()
            }
        }
}