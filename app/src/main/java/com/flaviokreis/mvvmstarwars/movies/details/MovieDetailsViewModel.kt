package com.flaviokreis.mvvmstarwars.movies.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flaviokreis.datasource.films.FilmRepository
import com.flaviokreis.datasource.films.model.Film
import com.flaviokreis.mvvmstarwars.commons.Resource
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

data class MovieDetailResult(
    val result: Resource<Film>? = null
)

class MovieDetailsViewModel(
    private val repository: FilmRepository
) : ViewModel() {
    private var _state  = mutableStateOf(MovieDetailResult())
    val state: State<MovieDetailResult> = _state

    fun fetchMovie(id: Int) {
        if (state.value.result != null) return

        viewModelScope.launch {
            repository.getFilms()
                .onStart {
                    _state.value = MovieDetailResult(result = Resource.Loading())
                }.collect { list ->
                    _state.value = list.firstOrNull { film -> film.id == id }?.let {
                        MovieDetailResult(result = Resource.Success(it))
                    } ?:  MovieDetailResult(result = Resource.Error("Film não encontrado"))

                }.runCatching {
                    val resource = Resource.Error<Film>("Erro ao tentar baixar o conteúdo")
                    _state.value = MovieDetailResult(result = resource)
                }
        }
    }
}