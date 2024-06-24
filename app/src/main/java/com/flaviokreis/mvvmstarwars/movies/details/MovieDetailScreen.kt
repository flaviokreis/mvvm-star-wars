package com.flaviokreis.mvvmstarwars.movies.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaviokreis.datasource.films.model.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieDetailScreen(id: Int, moviesFlow: Flow<List<Film>>) {

    val movie = moviesFlow
        .collectAsState(initial = emptyList())
        .value
        .find { it.id == id } ?: return

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
    MovieDetailScreen(1, flowOf(listOf(getFilmModel())))
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