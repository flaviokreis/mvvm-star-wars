package com.flaviokreis.mvvmstarwars.movies.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.flaviokreis.datasource.films.model.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun MoviesScreen(moviesFlow: Flow<List<Film>>, onItemClicked: (Int) -> Unit) {
    val movies = moviesFlow.collectAsState(listOf())

    LazyColumn {
        items(movies.value) { movie ->
            MovieItem(movie, onItemClicked)
        }
    }
}

@Preview
@Composable
fun MoviesScreenPreview() {
    MoviesScreen(flow {
        generateList()
    }) {

    }
}

private fun generateList() : List<Film> {
    return (1..20).map {
        getFilmModel(it)
    }
}

private fun getFilmModel(id: Int) = Film(
    id = id,
    title = "A New Hope $id",
    episodeId = id,
    openingCrawl = "It is a period of civil war. $id",
    director = "George Lucas",
    producer = "Gary Kurtz, Rick McCallum",
    releaseDate = "1977-05-25",
    url = "http://swapi.dev/api/films/1/"
)