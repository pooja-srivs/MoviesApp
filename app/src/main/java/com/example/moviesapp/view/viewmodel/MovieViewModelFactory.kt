package com.example.moviesapp.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.view.mapper.ViewDataMapper
import javax.inject.Inject

class MovieViewModelFactory @Inject constructor(
    private val movieRepository: MovieRepository,
    private val viewDataMapper: ViewDataMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(movieRepository, viewDataMapper) as T
    }
}