package com.flaviokreis.mvvmstarwars.movies.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flaviokreis.datasource.films.model.Film
import com.flaviokreis.mvvmstarwars.commons.Resource

@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel,
    onItemClicked: (Int) -> Unit
) {
    viewModel.fetchMovies()
    val moviesState = viewModel.state.value

    when(moviesState.result) {
        is Resource.Error -> Text(text = moviesState.result.message ?: "Erro Desconhecido")
        is Resource.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        is Resource.Success -> {
            moviesState.result.data?.let { result ->
                MoviesScreen(result, onItemClicked)
            }
        }
        null -> Text(text = "Algo deu errado")
    }


}

@Composable
fun MoviesScreen(movies: List<Film>, onItemClicked: (Int) -> Unit) {
    LazyColumn {
        items(movies) { movie ->
            MovieItem(movie, onItemClicked)
        }
    }
}

@Preview
@Composable
fun MoviesScreenPreview() {
    MoviesScreen(generateList()) { }
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