package com.flaviokreis.datasource.di

import com.flaviokreis.datasource.providers.database.AppDatabase
import com.flaviokreis.datasource.test.BaseKoinTest
import org.junit.Assert
import org.junit.Test
import org.koin.core.component.get

class DatabaseDITest : BaseKoinTest() {

    override fun getModules() = listOf(databaseDI)

    @Test
    fun `GIVEN database dependency injection module WHEN module is started THEN the room database is loaded`() {
        // GIVEN

        // WHEN
        val database = get<AppDatabase>()

        // THEN
        Assert.assertNotNull(database)
    }

    @Test
    fun `GIVEN database dependency injection module WHEN module is started THEN the film dao is loaded`() {
        // GIVEN

        // WHEN
        val database = get<AppDatabase>()

        // THEN
        Assert.assertNotNull(database)
    }
}