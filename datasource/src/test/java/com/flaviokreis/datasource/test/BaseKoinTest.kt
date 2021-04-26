package com.flaviokreis.datasource.test

import org.junit.After
import org.junit.Before
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

abstract class BaseKoinTest : KoinComponent {

    abstract fun getModules(): List<Module>

    @Before
    fun setUp() {
        startKoin { modules(getModules()) }
    }

    @After
    fun dearDown() {
        stopKoin()
    }
}