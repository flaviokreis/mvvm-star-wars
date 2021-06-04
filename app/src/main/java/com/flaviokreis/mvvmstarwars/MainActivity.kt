package com.flaviokreis.mvvmstarwars

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flaviokreis.datasource.films.FilmRepository
import com.flaviokreis.datasource.films.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val repository: FilmRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StarWarsApp(repository)
        }
    }

}

@Composable
fun StarWarsApp(
    repository: FilmRepository
) {
    MaterialTheme(
        colors = darkColors(
            primary = Color(0xFFFFE81F),
            secondary = Color.White,
        )
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Star Wars - Moves") }
                )
            }
        ) {
            MoviesScreen(repository)
        }
    }


}

@Composable
fun MoviesScreen(repository: FilmRepository) {
    val movies = repository.getFilms().collectAsState(emptyList())

    LazyColumn {
        items(movies.value) { movie ->
            MovieItem(movie)
        }
    }
}

@Composable
fun MovieItem(movie: Film) {
    val typography = MaterialTheme.typography
    val textColor = Color(0xFFFFE81F)

    Column(
        modifier = Modifier.padding(16f.dp)
    ) {
        Text(text = "Episode ${movie.episodeId}", style = typography.overline, color = textColor)
        Text(text = movie.title, style = typography.h5, color = textColor)
        Text(text = movie.director, style = typography.body2, color = textColor)
    }
}

@Preview
@Composable
fun DefaultPreview() {
    StarWarsApp(object : FilmRepository {
        override fun getFilms(): Flow<List<Film>> = flow {
            emit(generateList())
        }.flowOn(Dispatchers.Main)
    })
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