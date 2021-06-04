package com.flaviokreis.datasource.films.remote

import com.flaviokreis.datasource.commons.remote.RemoteMapper
import com.flaviokreis.datasource.films.model.Film
import com.flaviokreis.datasource.films.remote.model.FilmDTO
import com.flaviokreis.datasource.utils.getIdFromUrl

internal class FilmRemoteMapper : RemoteMapper<Film, FilmDTO> {
    override fun toModel(data: FilmDTO): Film {
        return Film(
            id = data.url.getIdFromUrl(),
            title = data.title,
            episodeId = data.episodeId,
            openingCrawl = data.openingCrawl,
            director = data.director,
            producer = data.producer,
            releaseDate = data.releaseDate,
            url = data.url
        )
    }
}