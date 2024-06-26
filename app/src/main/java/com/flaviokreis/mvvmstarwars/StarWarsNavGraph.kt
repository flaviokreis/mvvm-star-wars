package com.flaviokreis.mvvmstarwars

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flaviokreis.mvvmstarwars.MainDestinations.MOVIE_ID
import com.flaviokreis.mvvmstarwars.movies.details.MovieDetailScreen
import com.flaviokreis.mvvmstarwars.movies.list.MoviesScreen
import org.koin.androidx.compose.koinViewModel

object MainDestinations {
    const val MOVIES_ROUTE = "movies"
    const val MOVIE_DETAIL = "movie_detail"
    const val MOVIE_ID = "movieId"
}

@Composable
fun StarWarsNavGraph(
    navController: NavHostController = rememberNavController(),
    padding: PaddingValues
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = MainDestinations.MOVIES_ROUTE
    ) {
        composable(MainDestinations.MOVIES_ROUTE) {
            MoviesScreen(
                viewModel = koinViewModel(),
                onItemClicked = actions.navigateToMovie
            )
        }
        composable("${MainDestinations.MOVIE_DETAIL}/{$MOVIE_ID}") { backStackEntry ->
            MovieDetailScreen(
                viewModel = koinViewModel(),
                selectedId = backStackEntry.arguments?.getString(MOVIE_ID)?.toIntOrNull() ?: -1
            )
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToMovie: (id: Int) -> Unit = {
        navController.navigate("${MainDestinations.MOVIE_DETAIL}/$it")
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}