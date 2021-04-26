package com.flaviokreis.datasource.films.remote.model

import com.google.gson.annotations.SerializedName

internal data class FilmDTO(
    @SerializedName("title")
    val title: String,

    @SerializedName("episode_id")
    val episodeId: Int,

    @SerializedName("opening_crawl")
    val openingCrawl: String,

    @SerializedName("director")
    val director: String,

    @SerializedName("producer")
    val producer: String,

    @SerializedName("release_date")
    val releaseDate: String
)