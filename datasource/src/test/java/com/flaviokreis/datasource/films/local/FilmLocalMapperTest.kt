package com.flaviokreis.datasource.films.local

import com.flaviokreis.datasource.films.local.model.FilmEntity
import com.flaviokreis.datasource.films.model.Film
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class FilmLocalMapperTest {
    private val mapper: FilmLocalMapper = FilmLocalMapper()

    @Test
    fun `GIVEN film entity WHEN execute toModel THEN returns film model object`() {
        // GIVEN
        val entity = getFilmEntity()

        // WHEN
        val result = mapper.toModel(entity)

        // THEN
        assertNotNull(result)
        assertEquals(entity.title, result.title)
    }

    @Test
    fun `GIVEN film model WHEN execute toEntity THEN returns film entity object`() {
        // GIVEN
        val model = getFilmModel()

        // WHEN
        val result = mapper.toEntity(model)

        // THEN
        assertNotNull(result)
        assertEquals(model.title, result.title)
    }

    @Test
    fun `GIVEN film entities objects WHEN execute toModelList THEN returns film model list objects`() {
        // GIVEN
        val list = listOf(getFilmEntity())

        // WHEN
        val result = mapper.toModelList(list)

        // THEN
        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals(list.first().title, result.first().title)
    }

    private fun getFilmEntity() = FilmEntity(
        id = 1,
        title = "A New Hope",
        episodeId = 4,
        openingCrawl = "It is a period of civil war.",
        director = "George Lucas",
        producer = "Gary Kurtz, Rick McCallum",
        releaseDate = "1977-05-25",
        url = "http://swapi.dev/api/films/1/"
    )

    private fun getFilmModel() = Film(
        id = 1,
        title = "A New Hope",
        episodeId = 4,
        openingCrawl = "It is a period of civil war.",
        director = "George Lucas",
        producer = "Gary Kurtz, Rick McCallum",
        releaseDate = "1977-05-25",
        url = "http://swapi.dev/api/films/1/"
    )
}