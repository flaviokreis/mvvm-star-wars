package com.flaviokreis.mvvmstarwars.movies

import com.flaviokreis.mvvmstarwars.movies.list.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesDI = module {
    viewModel { MoviesViewModel(get()) }
}