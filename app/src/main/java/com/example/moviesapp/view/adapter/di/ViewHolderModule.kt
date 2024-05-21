package com.example.moviesapp.view.adapter.di

import com.example.moviesapp.view.adapter.viewholder.factory.ViewHolderTypeFactory
import com.example.moviesapp.view.adapter.viewholder.factory.ViewHolderTypeFactoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewHolderModule {

    @Binds
    @Singleton
    abstract fun providesViewHolder(viewHolderTypeFactoryImpl: ViewHolderTypeFactoryImpl): ViewHolderTypeFactory
}