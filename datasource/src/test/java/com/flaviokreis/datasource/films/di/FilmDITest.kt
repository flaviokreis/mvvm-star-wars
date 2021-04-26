package com.flaviokreis.datasource.films.di

import com.flaviokreis.datasource.di.networkDI
import com.flaviokreis.datasource.films.FilmRepository
import com.flaviokreis.datasource.films.FilmRepositoryImpl
import com.flaviokreis.datasource.films.remote.FilmRemoteDatasource
import com.flaviokreis.datasource.films.remote.FilmRemoteDatasourceImpl
import com.flaviokreis.datasource.films.remote.FilmRemoteMapper
import com.flaviokreis.datasource.films.remote.FilmRemoteService
import com.flaviokreis.datasource.test.BaseKoinTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.koin.core.component.get

class FilmDITest : BaseKoinTest() {
    override fun getModules() = listOf(
        networkDI,
        filmDI
    )

    @Test
    fun `GIVEN film dependency injection WHEN module is injected THEN the film remote service is created`() {
        // GIVEN

        // WHEN
        val value = get<FilmRemoteService>()

        //THEN
        assertNotNull(value)
    }

    @Test
    fun `GIVEN film dependency injection WHEN module is injected THEN the film remote mapper is created`() {
        // GIVEN

        // WHEN
        val value = get<FilmRemoteMapper>()

        //THEN
        assertNotNull(value)
    }

    @Test
    fun `GIVEN film dependency injection WHEN module is injected THEN the film remote data source is created`() {
        // GIVEN

        // WHEN
        val value = get<FilmRemoteDatasource>()

        //THEN
        assertNotNull(value)
        assertTrue(value is FilmRemoteDatasourceImpl)
    }

    @Test
    fun `GIVEN film dependency injection WHEN module is injected THEN the film repository is created`() {
        // GIVEN

        // WHEN
        val value = get<FilmRepository>()

        //THEN
        assertNotNull(value)
        assertTrue(value is FilmRepositoryImpl)
    }

}