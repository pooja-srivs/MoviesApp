package com.example.moviesapp.data.repository

import com.example.moviesapp.common.Result
import com.example.moviesapp.data.db.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieData(): Flow<Result<List<MovieEntity>>>

    suspend fun updateFavMovies(movies: MovieEntity)

    suspend fun removeFavMovie(movie: MovieEntity)

    suspend fun getFavMovies(): Flow<Result<List<MovieEntity>>>

}