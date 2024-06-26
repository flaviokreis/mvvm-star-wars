package com.flaviokreis.mvvmstarwars.movies.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaviokreis.datasource.films.model.Film
import com.flaviokreis.mvvmstarwars.commons.Resource

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailsViewModel,
    selectedId: Int
) {

    val state = viewModel.state.value

    viewModel.fetchMovie(selectedId)

    val typography = MaterialTheme.typography
    val textColor = Color(0xFFFFE81F)

    when(state.result) {
        is Resource.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16f.dp)
            ) {
                Text(
                    text = state.result.message ?: "Erro Desconhecido",
                    style = typography.body2, color = textColor
                )
            }
        }
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
            state.result.data?.let { result ->
                MovieDetailScreen(result)
            }
        }
        null -> Text(text = "Algo deu errado")
    }
}

@Composable
fun MovieDetailScreen(movie: Film) {

    val typography = MaterialTheme.typography
    val textColor = Color(0xFFFFE81F)

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16f.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20f.dp))
            Text(text = "Episode ${movie.episodeId}", style = typography.overline, color = textColor)
            Text(text = movie.title, style = typography.h3, color = textColor)
        }

        item {
            Spacer(modifier = Modifier.height(4f.dp))

            Text(text = "Director: ${movie.director}", style = typography.body2, color = textColor)
            Text(text = "Producer(s): ${movie.producer}", style = typography.body2, color = textColor)
            Text(text = "Release: ${movie.releaseDate}", style = typography.body2, color = textColor)

        }

        item {
            Spacer(modifier = Modifier.height(24f.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = movie.openingCrawl,
                    style = typography.h6, color = textColor)
            }

            Spacer(modifier = Modifier.height(20f.dp))
        }
    }
}

@Preview
@Composable
fun MovieDetailScreenPreview() {
    MovieDetailScreen(getFilmModel())
}

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