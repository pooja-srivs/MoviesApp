package com.example.moviesapp.data.source.remote

import app.cash.turbine.test
import com.example.moviesapp.data.apiservice.ApiMockService
import com.example.moviesapp.data.apiservice.ApiService
import com.example.moviesapp.data.model.MockData
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class RemoteDataSourceImplTest {

    private lateinit var apiService: ApiService
    private lateinit var apiMockService: ApiMockService
    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @Before
    fun setUp() {
        apiService = mockk()
        apiMockService = mockk()
        remoteDataSourceImpl = RemoteDataSourceImpl(apiService)
    }

    @Test
    fun testGetAllMovies() = runTest {
        val response = ApiMockService.getMockResponse()
        every { apiMockService.getMockResponse() } returns response

        remoteDataSourceImpl.getMovieData().test {
            val flowData = awaitItem()
            Assert.assertEquals(response, flowData)
            awaitComplete()
        }
    }
}