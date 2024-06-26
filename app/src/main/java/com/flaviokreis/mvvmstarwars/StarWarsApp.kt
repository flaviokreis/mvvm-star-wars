package com.flaviokreis.mvvmstarwars

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.flaviokreis.datasource.films.FilmRepository
import com.flaviokreis.datasource.films.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Star Wars - Moves") }
                )
            }
        ) {
            StarWarsNavGraph(navController)
        }
    }
}

@Preview
@Composable
fun StarWarsAppPreview() {
    StarWarsApp(object : FilmRepository {
        override fun getFilms(): Flow<List<Film>> = flow {
            emit(emptyList())
        }
    })
}