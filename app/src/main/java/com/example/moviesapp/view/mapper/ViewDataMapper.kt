package com.example.moviesapp.view.mapper

import com.example.moviesapp.data.db.MovieEntity
import com.example.moviesapp.view.adapter.MovieItem

interface ViewDataMapper {

    fun map(response: MovieItem): MovieEntity

    fun listMapToMovieItem(response: List<MovieEntity>): List<MovieItem>

    fun listMap(response: List<MovieItem>): List<MovieEntity>
}