package com.flaviokreis.datasource.films.local

import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.flaviokreis.datasource.films.local.model.FilmEntity
import com.flaviokreis.datasource.providers.database.AppDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class FilmLocalDaoTest {
    private lateinit var filmDao: FilmDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()

        filmDao = db.filmDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun `GIVEN inserted films to database WHEN getAll is called THEN returns all films`() =
        runBlocking {
            // GIVEN
            val expected = listOf(
                generateFilm(1),
                generateFilm(2),
                generateFilm(3)
            )

            filmDao.insertAll(expected)

            // WHEN
            val result = filmDao.getAll().first()

            // THEN
            assertEquals(expected, result)
        }

    @Test
    fun `GIVEN films database WHEN insertAll is called THEN returns all films`() =
        runBlocking {
            // GIVEN
            val expected1 = listOf(
                generateFilm(1)
            )

            val expected2 = listOf(
                generateFilm(1),
                generateFilm(2),
                generateFilm(3)
            )

            // WHEN
            filmDao.insertAll(expected1)
            val result1 = filmDao.getAll().first()

            filmDao.insertAll(expected2)
            val result2 = filmDao.getAll().first()

            // THEN
            assertEquals(expected1, result1)
            assertEquals(expected2, result2)
        }

    @Test
    fun `GIVEN inserted films to database WHEN deleteAll is called THEN remove all films`() =
        runBlocking {
            // GIVEN
            val expected = listOf(
                generateFilm(1),
                generateFilm(2),
                generateFilm(3)
            )

            filmDao.insertAll(expected)

            // WHEN
            val result1 = filmDao.getAll().first()

            filmDao.deleteAll()

            val result2 = filmDao.getAll().first()

            // THEN
            assertEquals(expected, result1)
            assertEquals(listOf<FilmEntity>(), result2)
        }

    private fun generateFilm(id: Int) : FilmEntity {
        return FilmEntity(
            id = id,
            title = "Film $id",
            episodeId = id,
            openingCrawl = "Film $id",
            director = "Director",
            producer = "Producer",
            releaseDate = "2021-01-01",
            url = "https://startwars.com"
        )
    }


}