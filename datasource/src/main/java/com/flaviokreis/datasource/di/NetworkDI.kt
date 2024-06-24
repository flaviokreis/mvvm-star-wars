package com.flaviokreis.datasource.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal val networkDI = module {

    factory(named("base_url")) { "https://swapi.dev/api/" }

    factory<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<String>(named("base_url")))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}