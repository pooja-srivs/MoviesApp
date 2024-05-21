package com.example.moviesapp.data.source.remote

import com.example.moviesapp.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getMovieData() : Flow<List<Movie>>
}