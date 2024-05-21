package com.example.moviesapp.di

import com.example.moviesapp.view.mapper.ViewDataMapper
import com.example.moviesapp.view.mapper.ViewDataMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ActivityModule {

    @Binds
    @Singleton
    abstract fun bindsViewDataMapperImpl(viewDataMapperImpl: ViewDataMapperImpl): ViewDataMapper

}