package com.flaviokreis.datasource.utils

import org.junit.Assert.*
import org.junit.Test

class StringUtilsTest {

    @Test
    fun `GIVEN valid string url WHEN getIdFromUrl method is called THEN the id is returned`() {
        // GIVEN
        val url = "https://swapi.dev/api/films/2/"
        val expected = 2

        // WHEN
        val result = url.getIdFromUrl()

        // THEN
        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN unformatted api string url WHEN getIdFromUrl method is called THEN the id is returned`() {
        // GIVEN
        val url = "https://swapi.dev/api/films/2"
        val expected = 2

        // WHEN
        val result = url.getIdFromUrl()

        // THEN
        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN invalid string url WHEN getIdFromUrl method is called THEN the -1 is returned`() {
        // GIVEN
        val url = "https://swapi.dev/api/films/"
        val expected = -1

        // WHEN
        val result = url.getIdFromUrl()

        // THEN
        assertEquals(expected, result)
    }
}