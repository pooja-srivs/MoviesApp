package com.example.moviesapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.common.Result
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.view.adapter.MovieItem
import com.example.moviesapp.view.mapper.ViewDataMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val mapper: ViewDataMapper
): ViewModel() {

    companion object{
        const val HOME = 0
        const val FAVOURITE = 1
    }
    private var homeMutableLiveData: MutableLiveData<Result<List<MovieItem>>> =
        MutableLiveData<Result<List<MovieItem>>>()
    val homeLiveData: LiveData<Result<List<MovieItem>>> by lazy { homeMutableLiveData }
    private var homeMutableMovieList = mutableListOf<MovieItem>()

    private var favMovieMutableLiveData = MutableLiveData<Result<Set<MovieItem>>>()
    val favMovieLiveData: LiveData<Result<Set<MovieItem>>> by lazy { favMovieMutableLiveData }

    fun getMovieData() {
        viewModelScope.launch {
            delay(1500)
            movieRepository.getMovieData()
                .onStart { homeMutableLiveData.value = Result.Loading }
                .catch { e -> homeMutableLiveData.value = Result.Error(Exception()) }
                .collect { response ->
                    when (response) {

                        is Result.Loading -> {
                            homeMutableLiveData.value = Result.Loading
                        }

                        is Result.Success -> {
                            homeMutableMovieList.clear()
                            response.data.map { item ->
                                homeMutableMovieList.add(
                                    MovieItem(
                                        id = item.id,
                                        title = item.title,
                                        subTitle = item.subtitle,
                                        isFav = item.isFavorite,
                                        type = HOME
                                    )
                                )
                            }
                            homeMutableLiveData.value =
                                Result.Success(homeMutableMovieList as List<MovieItem>)
                        }

                        is Result.Error -> {
                            homeMutableLiveData.value = Result.Error(Exception())
                        }
                    }
                }
        }
    }

    fun getFavMovieList() {
        viewModelScope.launch {
            movieRepository.getFavMovies()
                .onStart {  }
                .catch { e -> favMovieMutableLiveData.value = Result.Error(Exception())}
                .collect { response ->
                    when(response) {
                        is Result.Success -> {
                            val list = response.data
                            val mappedResponse = mapper.listMapToMovieItem(list)
                            favMovieMutableLiveData.value = Result.Success(mappedResponse.toSet())
                        }

                        is Result.Error -> {
                            favMovieMutableLiveData.value = Result.Error(Exception())
                        }

                        is Result.Loading -> {
                            favMovieMutableLiveData.value = Result.Loading
                        }
                    }
                }
        }
    }

    fun setFavMovie(item: MovieItem) {
        performListMappingAndInsertToDB(item.copy(isFav = true))
    }

    fun removeFromFavMovieList(item: MovieItem) {
        performMappingAndInsertToDB(item.copy(isFav = false))
    }

    private fun performMappingAndInsertToDB(movieItem: MovieItem) {
        val mappedResponse = mapper.map(movieItem)
        viewModelScope.launch {
            movieRepository.removeFavMovie(mappedResponse)
        }
    }

    private fun performListMappingAndInsertToDB(movieItem: MovieItem) {
        val mappedResponse = mapper.map(movieItem)
        viewModelScope.launch {
            movieRepository.updateFavMovies(mappedResponse)
        }
    }
}