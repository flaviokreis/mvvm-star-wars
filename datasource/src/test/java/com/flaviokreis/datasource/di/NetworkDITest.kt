package com.flaviokreis.datasource.di

import com.flaviokreis.datasource.test.BaseKoinTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.koin.core.component.get
import org.koin.core.qualifier.named
import retrofit2.Retrofit

class NetworkDITest: BaseKoinTest() {

    override fun getModules() = listOf(networkDI)

    @Test
    fun `GIVEN network dependency injection module WHEN module is started THEN the base url is loaded`() {
        // GIVEN

        // WHEN
        val baseUrl = get<String>(named("base_url"))

        // THEN
        assertNotNull(baseUrl)
    }

    @Test
    fun `GIVEN network dependency injection module WHEN module is started THEN the retrofit url is loaded`() {
        // GIVEN
        val baseUrl = get<String>(named("base_url"))

        // WHEN
        val retrofit = get<Retrofit>()

        // THEN
        assertNotNull(retrofit)
        assertEquals(baseUrl, retrofit.baseUrl().toString())
    }
}