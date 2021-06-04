package com.flaviokreis.datasource.films

import com.flaviokreis.datasource.films.local.FilmLocalDatasource
import com.flaviokreis.datasource.films.remote.FilmRemoteDatasource
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
class FilmRepositoryImplTest {
    private val localDatasource: FilmLocalDatasource = mockk()
    private val remoteDatasource: FilmRemoteDatasource = mockk()

    @Test
    fun `GIVEN film local datasource WHEN local datasource returns films THEN returns result from database`() =
        runBlocking {
            // GIVEN
            val repository = FilmRepositoryImpl(localDatasource, remoteDatasource)

            coEvery { localDatasource.getFilms() } returns flowOf(listOf(mockk()))

            // THEN
            val result = repository.getFilms().first()

            Assert.assertEquals(1, result.size)
            coVerify {
                localDatasource.getFilms()
            }
        }

    @Test
    fun `GIVEN film remote datasource WHEN local datasource has empty list THEN returns content from remote`() =
        runBlocking {
            // GIVEN
            val repository = FilmRepositoryImpl(localDatasource, remoteDatasource)

            coEvery { remoteDatasource.getFilms() } returns flowOf(listOf(mockk()))
            coEvery { localDatasource.addFilms(any()) } just Runs
            coEvery { localDatasource.getFilms() } returnsMany listOf(
                flowOf(emptyList()),
                flowOf(listOf(mockk()))
            )

            // THEN
            val result = repository.getFilms().drop(1).first()

            Assert.assertEquals(1, result.size)
            coVerifySequence {
                localDatasource.getFilms()
                remoteDatasource.getFilms()
                localDatasource.addFilms(any())
                localDatasource.getFilms()
            }
        }

    @Test(expected = SocketTimeoutException::class)
    fun `GIVEN film datasource WHEN datasource returns throws THEN flow emit throws`() =
        runBlocking {
            // GIVEN
            val repository = FilmRepositoryImpl(localDatasource, remoteDatasource)

            coEvery { localDatasource.getFilms() } returns flowOf(emptyList())
            coEvery { remoteDatasource.getFilms() } throws SocketTimeoutException("No connectivity")

            // WHEN
            val result = repository.getFilms().drop(1).first()

            // THEN
            Assert.assertNull(result)
        }
}