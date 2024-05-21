package com.example.moviesapp.data.source.remote

import com.example.moviesapp.data.model.MockData
import com.example.moviesapp.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor() : RemoteDataSource {

    override fun getMovieData(): Flow<List<Movie>> =
       flow { emit(MockData.movies) }
}