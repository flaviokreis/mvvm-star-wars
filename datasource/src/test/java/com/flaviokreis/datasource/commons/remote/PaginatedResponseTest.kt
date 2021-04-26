package com.flaviokreis.datasource.commons.remote

import org.junit.Assert.*
import org.junit.Test

class PaginatedResponseTest {
    @Test
    fun `GIVEN paginated response WHEN instantiated it THEN verify default values`() {
        // GIVEN

        // WHEN
        val paginatedResponse = PaginatedResponse<String>()

        // THEN
        assertEquals(0, paginatedResponse.count)
        assertNull(paginatedResponse.next)
        assertNull(paginatedResponse.previous)
        assertTrue(paginatedResponse.results.isEmpty())
    }
}