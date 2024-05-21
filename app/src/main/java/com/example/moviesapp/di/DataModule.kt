package com.example.moviesapp.di

import com.example.moviesapp.common.mapper.DataMapper
import com.example.moviesapp.common.mapper.DataMapperImpl
import com.example.moviesapp.data.mapper.MovieResponseMapper
import com.example.moviesapp.data.mapper.MovieResponseMapperImpl
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.data.repository.MovieRepositoryImpl
import com.example.moviesapp.data.source.local.LocalDataSource
import com.example.moviesapp.data.source.local.LocalDataSourceImpl
import com.example.moviesapp.data.source.remote.RemoteDataSource
import com.example.moviesapp.data.source.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindsRemoteDataSource(dataSource: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    @Singleton
    abstract fun bindsRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    abstract fun bindsMovieResponseMapper(movieResponseMapper: MovieResponseMapperImpl): MovieResponseMapper

    @Binds
    @Singleton
    abstract fun bindsDataMapperImpl(dataMapper: DataMapperImpl): DataMapper
}