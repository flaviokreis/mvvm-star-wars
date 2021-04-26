package com.flaviokreis.datasource.films.remote

import com.flaviokreis.datasource.commons.remote.PaginatedResponse
import com.flaviokreis.datasource.films.remote.model.FilmDTO
import retrofit2.http.GET

internal interface FilmRemoteService {
    @GET("films")
    suspend fun getFilms(): PaginatedResponse<FilmDTO>
}