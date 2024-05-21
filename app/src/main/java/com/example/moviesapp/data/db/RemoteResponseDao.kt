package com.example.moviesapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RemoteResponseDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovieById(movieId: String): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies WHERE isFavorite = true")
    fun getAllFavMovies(): Flow<List<MovieEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun removeFromFavMovie(movie: MovieEntity)

}