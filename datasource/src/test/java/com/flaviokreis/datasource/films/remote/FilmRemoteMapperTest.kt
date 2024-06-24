package com.flaviokreis.datasource.films.remote

import com.flaviokreis.datasource.films.remote.model.FilmDTO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class FilmRemoteMapperTest {
    private val mapper: FilmRemoteMapper = FilmRemoteMapper()

    @Test
    fun `GIVEN film data transfer object WHEN execute toModel THEN returns film model object`() {
        // GIVEN
        val dto = FilmDTO(
            title = "A New Hope",
            episodeId = 4,
            openingCrawl = "It is a period of civil war.",
            director = "George Lucas",
            producer = "Gary Kurtz, Rick McCallum",
            releaseDate = "1977-05-25",
            url = "http://swapi.dev/api/films/1/"
        )

        // WHEN
        val result = mapper.toModel(dto)

        // THEN
        assertNotNull(result)
        assertEquals(dto.title, result.title)
    }

    @Test
    fun `GIVEN film data transfer list objects WHEN execute toModelList THEN returns film model list objects`() {
        // GIVEN
        val list = listOf(
            FilmDTO(
                title = "A New Hope",
                episodeId = 4,
                openingCrawl = "It is a period of civil war.",
                director = "George Lucas",
                producer = "Gary Kurtz, Rick McCallum",
                releaseDate = "1977-05-25",
                url = "http://swapi.dev/api/films/1/"
            )
        )

        // WHEN
        val result = mapper.toModelList(list)

        // THEN
        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals(list.first().title, result.first().title)
    }
}