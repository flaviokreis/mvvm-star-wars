package com.flaviokreis.datasource.films.di

import com.flaviokreis.datasource.films.FilmRepository
import com.flaviokreis.datasource.films.FilmRepositoryImpl
import com.flaviokreis.datasource.films.local.FilmLocalDatasource
import com.flaviokreis.datasource.films.local.FilmLocalDatasourceImpl
import com.flaviokreis.datasource.films.local.FilmLocalMapper
import com.flaviokreis.datasource.films.remote.FilmRemoteDatasource
import com.flaviokreis.datasource.films.remote.FilmRemoteDatasourceImpl
import com.flaviokreis.datasource.films.remote.FilmRemoteMapper
import com.flaviokreis.datasource.films.remote.FilmRemoteService
import org.koin.dsl.module
import retrofit2.Retrofit

internal val filmDI = module {
    factory {
        get<Retrofit>().create(FilmRemoteService::class.java)
    }

    factory { FilmRemoteMapper() }
    factory<FilmRemoteDatasource> { FilmRemoteDatasourceImpl(get(), get()) }

    factory { FilmLocalMapper() }
    factory<FilmLocalDatasource> { FilmLocalDatasourceImpl(get(), get()) }

    factory<FilmRepository> { FilmRepositoryImpl(get(), get()) }
}