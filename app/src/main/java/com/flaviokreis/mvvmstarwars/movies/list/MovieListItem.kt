package com.flaviokreis.mvvmstarwars.movies.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaviokreis.datasource.films.model.Film

@Composable
fun MovieItem(movie: Film, onItemClicked: (Int) -> Unit) {
    val typography = MaterialTheme.typography
    val textColor = Color(0xFFFFE81F)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClicked(movie.id) })
            .padding(16f.dp)
    ) {
        Text(text = "Episode ${movie.episodeId}", style = typography.body2, color = textColor)
        Text(text = movie.title, style = typography.h5, color = textColor)
        Text(text = movie.director, style = typography.body1, color = textColor)
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(getFilmModel()) {

    }
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