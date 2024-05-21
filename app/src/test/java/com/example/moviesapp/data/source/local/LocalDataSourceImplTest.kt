package com.example.moviesapp.data.source.local

import com.example.moviesapp.data.db.LastRefreshTimeDao
import com.example.moviesapp.data.db.LastRefreshTimeEntity
import com.example.moviesapp.data.db.MovieEntity
import com.example.moviesapp.data.db.RemoteResponseDao
import com.example.moviesapp.mock.MockResponse
import com.nhaarman.mockitokotlin2.mock
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalDataSourceImplTest {

    private lateinit var lastRefreshTimeDao: LastRefreshTimeDao
    private lateinit var remoteResponseDao: RemoteResponseDao
    private lateinit var localDataSourceImpl: LocalDataSourceImpl

    @Before
    fun setUp() {
        lastRefreshTimeDao = mockk()
        remoteResponseDao = mockk()
        localDataSourceImpl = LocalDataSourceImpl(lastRefreshTimeDao, remoteResponseDao)
    }

    @Test
    fun testRefreshLastRefreshTime() = runTest {
        val expected = mockk<LastRefreshTimeEntity>()
        coEvery { lastRefreshTimeDao.getLastRefreshTime() } coAnswers {
            expected
        }
        val response = localDataSourceImpl.getLastRefreshTime()

        Assert.assertEquals(expected, response)
    }

    @Test
    fun testSaveAndInsertLastRefreshTime() = runTest {
        val input = 2877687990
        coJustRun { lastRefreshTimeDao.insert(any()) }
        localDataSourceImpl.saveLastRefreshTime(input)

        coVerify { lastRefreshTimeDao.insert(LastRefreshTimeEntity(lastRefreshTime = input)) }
    }

    @Test
    fun testRemoveFavoriteMovie() = runTest {
        val input = mockk<MovieEntity>()
        coJustRun { remoteResponseDao.removeFromFavMovie(any()) }

        localDataSourceImpl.removeFavMovies(input)

        coVerify { remoteResponseDao.removeFromFavMovie(input) }
    }

    @Test
    fun testInsertOrUpdateMovies() = runTest {
        val input = mock<List<MovieEntity>>()
        coJustRun { remoteResponseDao.insertMovie(input) }

        localDataSourceImpl.insertOrUpdateMovies(input)

        coVerify { remoteResponseDao.insertMovie(input) }
    }

    @Test
    fun testUpdateFavMovies() = runTest {
        val input = mockk<MovieEntity>()
        coJustRun { remoteResponseDao.updateMovie(any()) }

        localDataSourceImpl.updateFavMovies(input)

        coVerify { remoteResponseDao.updateMovie(input) }
    }

    @Test
    fun testGetMovies() = runTest {
        val expected = MockResponse.current
        coEvery {  remoteResponseDao.getAllMovies() } coAnswers {
            flow { emit(expected) }
        }
        val flowResult = localDataSourceImpl.getMovies()
        val flowResultArr = flowResult.toList()
        Assert.assertEquals(1, flowResultArr.size)
        Assert.assertEquals(expected, flowResultArr.first())
    }

    @Test
    fun testGetAllFavMovies() = runTest {
        val expected = MockResponse.current
        coEvery { remoteResponseDao.getAllFavMovies() } coAnswers {
            flow { emit(expected) }
        }
        val flowResult = localDataSourceImpl.getAllFavMovies()
        val flowResultArr = flowResult.toList()
        //check for single emission
        Assert.assertEquals(1, flowResultArr.size)
        Assert.assertEquals(expected, flowResultArr.first())
    }
}