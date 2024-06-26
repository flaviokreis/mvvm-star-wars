package com.flaviokreis.mvvmstarwars.movies.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flaviokreis.datasource.films.FilmRepository
import com.flaviokreis.datasource.films.model.Film
import com.flaviokreis.mvvmstarwars.commons.Resource
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

data class MoviesResult(
    val result: Resource<List<Film>>? = null
)

class MoviesViewModel(
    private val repository: FilmRepository
) : ViewModel() {
    private var _state  = mutableStateOf(MoviesResult())
    val state: State<MoviesResult> = _state

    fun fetchMovies() {
        if (state.value.result != null) return

        viewModelScope.launch {
            repository.getFilms()
                .onStart {
                    _state.value = MoviesResult(result = Resource.Loading())
                }.collect {
                    _state.value = MoviesResult(result = Resource.Success(it))
                }.runCatching {
                    val resource = Resource.Error<List<Film>>("Erro ao tentar baixar o conteúdo")
                    _state.value = MoviesResult(result = resource)
                }
        }
    }
}