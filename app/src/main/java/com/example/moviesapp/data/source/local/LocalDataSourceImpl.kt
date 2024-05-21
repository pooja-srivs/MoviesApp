package com.example.moviesapp.data.source.local

import com.example.moviesapp.data.db.LastRefreshTimeDao
import com.example.moviesapp.data.db.LastRefreshTimeEntity
import com.example.moviesapp.data.db.MovieEntity
import com.example.moviesapp.data.db.RemoteResponseDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val lastRefreshTimeDao: LastRefreshTimeDao,
    private val remoteResponseDao: RemoteResponseDao
): LocalDataSource{

    override suspend fun getLastRefreshTime(): LastRefreshTimeEntity =
        lastRefreshTimeDao.getLastRefreshTime()

    override suspend fun saveLastRefreshTime(lastRefreshTime: Long) =
        lastRefreshTimeDao.insert(LastRefreshTimeEntity(lastRefreshTime = lastRefreshTime))

    override suspend fun removeFavMovies(movieEntity: MovieEntity) =
        remoteResponseDao.removeFromFavMovie(movieEntity)

    override suspend fun insertOrUpdateMovies(movies: List<MovieEntity>) =
        remoteResponseDao.insertMovie(movies)

    override suspend fun updateFavMovies(movies: MovieEntity) =
        remoteResponseDao.updateMovie(movies)

    override fun getMovies(): Flow<List<MovieEntity>> =
        remoteResponseDao.getAllMovies()

    override fun getAllFavMovies(): Flow<List<MovieEntity>> =
        remoteResponseDao.getAllFavMovies()
}