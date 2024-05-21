package com.example.moviesapp.data.source.remote

import com.example.moviesapp.data.apiservice.ApiMockService
import com.example.moviesapp.data.apiservice.ApiService
import com.example.moviesapp.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override fun getMovieData(): Flow<List<Movie>> =
       flow {
           val mockRes = ApiMockService.getMockResponse()
           emit(mockRes)
       }
}