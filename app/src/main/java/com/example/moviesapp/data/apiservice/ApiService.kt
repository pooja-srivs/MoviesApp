package com.example.moviesapp.data.apiservice

import com.example.moviesapp.data.model.Movie
import retrofit2.http.GET

interface ApiService {

    @GET("/getMovieListData")
    fun getMovieListData() : List<Movie>

}