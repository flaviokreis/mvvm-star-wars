package com.flaviokreis.datasource.films.local

import com.flaviokreis.datasource.commons.database.DatabaseMapper
import com.flaviokreis.datasource.films.local.model.FilmEntity
import com.flaviokreis.datasource.films.model.Film

internal class FilmLocalMapper : DatabaseMapper<Film, FilmEntity> {
    override fun toModel(data: FilmEntity): Film {
        return Film(
            id = data.id,
            title = data.title,
            episodeId = data.episodeId,
            openingCrawl = data.openingCrawl,
            director = data.director,
            producer = data.producer,
            releaseDate = data.releaseDate,
            url = data.url
        )
    }

    override fun toEntity(model: Film): FilmEntity {
        return FilmEntity(
            id = model.id,
            title = model.title,
            episodeId = model.episodeId,
            openingCrawl = model.openingCrawl,
            director = model.director,
            producer = model.producer,
            releaseDate = model.releaseDate,
            url = model.url
        )
    }

}