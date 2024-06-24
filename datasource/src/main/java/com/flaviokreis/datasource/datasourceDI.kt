package com.flaviokreis.datasource

import com.flaviokreis.datasource.di.databaseDI
import com.flaviokreis.datasource.di.networkDI
import com.flaviokreis.datasource.films.di.filmDI

val datasourceDI = listOf(
    networkDI,
    databaseDI,
    filmDI
)