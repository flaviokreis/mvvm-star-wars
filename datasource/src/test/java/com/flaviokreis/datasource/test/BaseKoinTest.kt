package com.flaviokreis.datasource.test

import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

abstract class BaseKoinTest : KoinComponent {

    abstract fun getModules(): List<Module>

    @Before
    fun setUp() {
        startKoin {
            androidContext(mockk(relaxed = true))
            modules(getModules())
        }
    }

    @After
    fun dearDown() {
        stopKoin()
    }
}