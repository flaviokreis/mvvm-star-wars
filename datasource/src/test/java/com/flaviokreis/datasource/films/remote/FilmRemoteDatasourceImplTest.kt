package com.flaviokreis.datasource.films.remote

import com.flaviokreis.datasource.commons.remote.PaginatedResponse
import com.flaviokreis.datasource.films.remote.model.FilmDTO
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
class FilmRemoteDatasourceImplTest {
    private val remoteService: FilmRemoteService = mockk()

    @Test
    fun `GIVEN film service WHEN service returns response THEN returns film dto list`() = runBlockingTest {
        // GIVEN
        val datasource = FilmRemoteDatasourceImpl(remoteService)
        val response: PaginatedResponse<FilmDTO> = mockk()

        coEvery { response.results } returns listOf(mockk())
        coEvery { remoteService.getFilms() } returns response

        // THEN
        val result = datasource.getFilms().first()

        assertEquals(1, result.size)
        coVerifySequence {
            remoteService.getFilms()
            response.results
        }
    }

    @Test(expected = SocketTimeoutException::class)
    fun `GIVEN film service WHEN service returns throws THEN flow emit throws`() = runBlockingTest {
        // GIVEN
        val datasource = FilmRemoteDatasourceImpl(remoteService)

        coEvery { remoteService.getFilms() } throws SocketTimeoutException("No connectivity")

        // WHEN
        val result = datasource.getFilms().first()

        // THEN
        assertNull(result)
    }
}