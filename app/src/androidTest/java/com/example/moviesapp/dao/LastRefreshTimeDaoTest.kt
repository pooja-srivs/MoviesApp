package com.example.moviesapp.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.moviesapp.data.db.LastRefreshTimeDao
import com.example.moviesapp.data.db.LastRefreshTimeEntity
import com.example.moviesapp.data.db.MovieDatabase
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LastRefreshTimeDaoTest {

    private lateinit var database: MovieDatabase
    private lateinit var dao: LastRefreshTimeDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.lastRefreshTimeDao()
    }

    @Test
    fun testLastRefreshTime() = runTest {
        val entity = LastRefreshTimeEntity(lastRefreshTime = System.currentTimeMillis())
        dao.insert(entity)

        val lastRefreshTime: LastRefreshTimeEntity = dao.getLastRefreshTime()

        Assert.assertEquals(lastRefreshTime, entity)
    }

    @Test
    fun tearDown() {
        database.close()
    }
}