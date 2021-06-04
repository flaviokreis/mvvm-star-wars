package com.flaviokreis.datasource.commons

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class NetworkBoundResourceKtTest {

    @Test
    fun `GIVEN local content WHEN should not fetch from remote THEN returns local content`() =
        runBlocking {
            // GIVEN
            val local = "local"
            val remote = "remote"

            var saved: String? = null

            val resource = networkBoundResource(
                fetchFromLocal = {
                    flow { emit(local) }
                },
                fetchFromRemote = {
                    flow { emit(remote) }
                },
                shouldFetchFromRemote = {
                    false
                },
                saveRemoteData = {
                    saved = it
                }
            ).first()

            assertNull(saved)
            assertEquals(local, resource)
        }

    @Test
    fun `GIVEN remote content WHEN should fetch from remote THEN returns remote content`() =
        runBlocking {
            // GIVEN
            val remote = "remote"
            var local = ""

            val resource = networkBoundResource(
                fetchFromLocal = {
                    flow { emit(local) }
                },
                fetchFromRemote = {
                    flow { emit(remote) }
                },
                shouldFetchFromRemote = {
                    true
                },
                saveRemoteData = {
                    local = it
                }
            ).toList()

            assertEquals("", resource.first())
            assertEquals(remote, resource.last())
            assertEquals(remote, local)
        }
}