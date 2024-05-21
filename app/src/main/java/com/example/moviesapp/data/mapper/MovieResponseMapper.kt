package com.example.moviesapp.data.mapper

import com.example.moviesapp.data.db.MovieEntity
import com.example.moviesapp.data.model.Movie

interface MovieResponseMapper {

    fun map(response: Movie): MovieEntity

    fun listMap(response: List<Movie>): List<MovieEntity>
}