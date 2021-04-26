package com.flaviokreis.datasource.films

import com.flaviokreis.datasource.films.model.Film
import com.flaviokreis.datasource.films.remote.FilmRemoteDatasource
import com.flaviokreis.datasource.films.remote.FilmRemoteMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
class FilmRepositoryImplTest {
    private val datasource: FilmRemoteDatasource = mockk()
    private val remoteMapper: FilmRemoteMapper = mockk()

    @Test
    fun `GIVEN film remote datasource WHEN remote datasource returns films THEN result is converted to model`() = runBlockingTest {
        // GIVEN
        val repository = FilmRepositoryImpl(datasource, remoteMapper)

        coEvery { datasource.getFilms() } returns flowOf(listOf(mockk()))
        coEvery { remoteMapper.toModelList(any()) } returns listOf(
            Film(
                title = "A New Hope",
                episodeId = 4,
                openingCrawl = "It is a period of civil war.",
                director = "George Lucas",
                producer = "Gary Kurtz, Rick McCallum",
                releaseDate = "1977-05-25"
            )
        )

        // THEN
        val result = repository.getFilms().first()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("A New Hope", result.first().title)
        coVerify {
            datasource.getFilms()
            remoteMapper.toModelList(any())
        }
    }

    @Test(expected = SocketTimeoutException::class)
    fun `GIVEN film datasource WHEN datasource returns throws THEN flow emit throws`() = runBlockingTest {
        // GIVEN
        val repository = FilmRepositoryImpl(datasource, remoteMapper)

        coEvery { datasource.getFilms() } throws SocketTimeoutException("No connectivity")

        // WHEN
        val result = datasource.getFilms().first()

        // THEN
        Assert.assertNull(result)
    }
}