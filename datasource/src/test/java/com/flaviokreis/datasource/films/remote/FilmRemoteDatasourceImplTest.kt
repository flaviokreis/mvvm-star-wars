package com.flaviokreis.datasource.films.remote

import com.flaviokreis.datasource.commons.remote.PaginatedResponse
import com.flaviokreis.datasource.films.remote.model.FilmDTO
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertNull
import org.junit.Test
import java.net.SocketTimeoutException

class FilmRemoteDatasourceImplTest {
    @Test
    fun `GIVEN film service WHEN service returns response THEN returns film dto list`() =
        runBlocking {
            // GIVEN
            val remoteService: FilmRemoteService = mockk()
            val filmMapper: FilmRemoteMapper = mockk()
            val datasource = FilmRemoteDatasourceImpl(remoteService, filmMapper)

            val response: PaginatedResponse<FilmDTO> = mockk()

            every { response.results } returns listOf(mockk())
            coEvery { remoteService.getFilms() } returns response
            every { filmMapper.toModelList(any()) } returns listOf(mockk())

            // THEN
            val result = datasource.getFilms().first()

            Assert.assertTrue(result.isNotEmpty())
            coVerifySequence {
                remoteService.getFilms()
                response.results
                filmMapper.toModelList(any())
            }
        }

    @Test(expected = SocketTimeoutException::class)
    fun `GIVEN film service WHEN service returns throws THEN flow emit throws`() =
        runBlocking {
            // GIVEN
            val remoteService: FilmRemoteService = mockk()
            val datasource = FilmRemoteDatasourceImpl(remoteService, mockk())

            coEvery { remoteService.getFilms() } throws SocketTimeoutException("No connectivity")

            // WHEN
            val result = datasource.getFilms().first()

            // THEN
            assertNull(result)
        }
}