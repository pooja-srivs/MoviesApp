package com.example.moviesapp.data.repository

import com.example.moviesapp.common.Result
import com.example.moviesapp.data.db.MovieEntity
import com.example.moviesapp.data.mapper.MovieResponseMapper
import com.example.moviesapp.data.source.local.LocalDataSource
import com.example.moviesapp.data.source.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mapper: MovieResponseMapper,
    private val dispatcher: CoroutineDispatcher
): MovieRepository {

    companion object {
        private const val REFRESH_INTERVAL_MS = 15 * 60 * 1000
    }

    override fun getMovieData(): Flow<Result<List<MovieEntity>>> = flow {
        try {
            if (isDataStale()) {
                val response = remoteDataSource.getMovieData()
                val mappedResponse = mapper.listMap(response.toList().flatten())
                localDataSource.saveLastRefreshTime(System.currentTimeMillis())
                localDataSource.insertOrUpdateMovies(mappedResponse)
                emit(Result.Success(mappedResponse))
            } else {
                localDataSource.getMovies().collect{ cachedData ->
                    emit(Result.Success(cachedData))
                }
            }
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /*
   * this function is to check the expiry of the stored data
   * */
    private suspend fun isDataStale(): Boolean {
        val lastRefreshTimeEntity = localDataSource.getLastRefreshTime()
        val lastRefreshTime =  lastRefreshTimeEntity.lastRefreshTime ?: 0
        if (lastRefreshTime.toInt() == 0) {
            return true
        }

        val res = System.currentTimeMillis() - lastRefreshTime > REFRESH_INTERVAL_MS
        return res
    }


    override suspend fun updateFavMovies(movies: MovieEntity) {
        withContext(dispatcher) {
            try {
                localDataSource.updateFavMovies(movies)
            } catch (e: Exception) {
            }
        }
    }

    override suspend fun removeFavMovie(movie: MovieEntity) {
        withContext(dispatcher) {
            try {
                localDataSource.removeFavMovies(movie)
            } catch (e: Exception) {
            }
        }
    }

    override suspend fun getFavMovies(): Flow<Result<List<MovieEntity>>> =
        flow {
            try {
                localDataSource.getAllFavMovies().collect { cachedData ->
                    emit(Result.Success(cachedData))
                }
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}