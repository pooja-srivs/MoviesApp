package com.example.moviesapp.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.example.moviesapp.mock.MockResponse
import com.example.moviesapp.data.db.MovieDatabase
import com.example.moviesapp.data.db.MovieEntity
import com.example.moviesapp.data.db.RemoteResponseDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RemoteResponseDaoTest {

    private lateinit var database: MovieDatabase
    private lateinit var dao: RemoteResponseDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.remoteResponseDao()
    }

    @Test
    fun testAllMoviesInsertAndRetrieve() = runTest {
        val movies = listOf(
                MovieEntity(
                    id = 1,
                    title = "Movie",
                    subtitle = "Summary",
                    isFavorite = true,
                    type = 0
                )
            )
        dao.insertMovie(movies)

        val flowData = dao.getAllMovies().first()
        Assert.assertEquals(1, flowData.size)
        Assert.assertEquals(movies.first(), flowData.first())
    }

    @Test
    fun testUpdateAMovie() = runTest {
        val current = listOf(
            MovieEntity(
                id = 1,
                title = "Movie",
                subtitle = "Summary",
                isFavorite = false,
                type = 0
            )
        )
        dao.insertMovie(current)

        val updated = MovieEntity(
                id = 1,
                title = "Movie",
                subtitle = "Summary",
                isFavorite = true,
                type = 0
            )
        dao.updateMovie(updated)

        // UncompletedCoroutinesError
        // Use .first(), returns the first element emitted by the flow and then cancels flow's collection
        val flowData = dao.getAllMovies().first()

        Assert.assertEquals(1, flowData.size)
        Assert.assertEquals(updated, flowData.first())
    }

    @Test
    fun testFetchingAllFavMovies() = runTest {
        val current = MockResponse.current
        dao.insertMovie(current)

        dao.getAllFavMovies().test {
            val flowData = awaitItem()
            Assert.assertEquals(2, flowData.size)
            Assert.assertEquals(current[0], flowData.first())
            Assert.assertEquals(current[2], flowData.last())
            cancel()
        }
    }

    @Test
    fun testRemoveFromFavMovies() = runTest {
        val expected =  MockResponse.expected
        val current = MockResponse.current
        dao.insertMovie(current)

        val removeItem = MovieEntity(
            id = 3,
            title = "Movie",
            subtitle = "Summary",
            isFavorite = false,
            type = 0
        )
        dao.removeFromFavMovie(removeItem)

        dao.getAllFavMovies().test {
            val flowData = awaitItem()
            Assert.assertEquals(1, flowData.size)
            Assert.assertEquals(expected, flowData)
            cancelAndIgnoreRemainingEvents()
        }
    }

}