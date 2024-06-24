package com.flaviokreis.datasource.films.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
internal data class FilmEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "episode_id")
    val episodeId: Int,

    @ColumnInfo(name = "opening_crawl")
    val openingCrawl: String,

    @ColumnInfo(name = "director")
    val director: String,

    @ColumnInfo(name = "producer")
    val producer: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "url")
    val url: String
)