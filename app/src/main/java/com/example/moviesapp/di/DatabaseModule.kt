package com.example.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.example.moviesapp.data.db.LastRefreshTimeDao
import com.example.moviesapp.data.db.MovieDatabase
import com.example.moviesapp.data.db.RemoteResponseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context = context, MovieDatabase::class.java,
            "movie_db"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): RemoteResponseDao = database.remoteResponseDao()

    @Provides
    fun provideRemoteResponseDao(database: MovieDatabase): LastRefreshTimeDao = database.lastRefreshTimeDao()
}