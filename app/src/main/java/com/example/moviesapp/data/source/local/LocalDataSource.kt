package com.example.moviesapp.data.source.local

import com.example.moviesapp.data.db.LastRefreshTimeEntity
import com.example.moviesapp.data.db.MovieEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getLastRefreshTime(): LastRefreshTimeEntity

    suspend fun saveLastRefreshTime(lastRefreshTime: Long)

    fun getMovies(): Flow<List<MovieEntity>>

    suspend fun insertOrUpdateMovies(movies: List<MovieEntity>)

    suspend fun updateFavMovies(movies: MovieEntity)

    suspend fun removeFavMovies(movieEntity: MovieEntity)

    fun getAllFavMovies(): Flow<List<MovieEntity>>

}