package com.flaviokreis.datasource.films.remote

import com.flaviokreis.datasource.commons.remote.RemoteMapper
import com.flaviokreis.datasource.films.model.Film
import com.flaviokreis.datasource.films.remote.model.FilmDTO

internal class FilmRemoteMapper : RemoteMapper<Film, FilmDTO> {
    override fun toModel(data: FilmDTO): Film {
        return Film(
            title = data.title,
            episodeId = data.episodeId,
            openingCrawl = data.openingCrawl,
            director = data.director,
            producer = data.producer,
            releaseDate = data.releaseDate
        )
    }

    override fun toDto(model: Film): FilmDTO {
        return FilmDTO(
            title = model.title,
            episodeId = model.episodeId,
            openingCrawl = model.openingCrawl,
            director = model.director,
            producer = model.producer,
            releaseDate = model.releaseDate
        )
    }

}