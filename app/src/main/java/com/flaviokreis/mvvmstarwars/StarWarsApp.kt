package com.flaviokreis.mvvmstarwars

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.darkColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.flaviokreis.datasource.films.FilmRepository
import com.flaviokreis.datasource.films.model.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

        val currentBackStackEntry by navController.currentBackStackEntryAsState()

        val showBackButton by remember(currentBackStackEntry) {
            derivedStateOf {
                navController.previousBackStackEntry != null
            }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Star Wars - Moves") },
                    navigationIcon = if (showBackButton) {
                        {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Voltar"
                                )
                            }
                        }
                    } else {
                        null
                    }
                )
            }
        ) { padding ->
            StarWarsNavGraph(navController, padding)
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