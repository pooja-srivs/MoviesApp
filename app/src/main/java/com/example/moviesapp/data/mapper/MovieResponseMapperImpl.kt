package com.example.moviesapp.data.mapper

import com.example.moviesapp.common.mapper.DataMapper
import com.example.moviesapp.data.db.MovieEntity
import com.example.moviesapp.data.model.Movie
import javax.inject.Inject

class MovieResponseMapperImpl @Inject constructor(
    private val dataMapper: DataMapper
): MovieResponseMapper {
    companion object {
        private const val HOME = 0
        private const val FAV = 1
    }

    override fun map(response: Movie): MovieEntity =
        dataMapper.map(response) {
            MovieEntity(
                id = it.id,
                title = it.name,
                subtitle = it.description,
                isFavorite = false,
                type = FAV
            )
        }


    override fun listMap(response: List<Movie>): List<MovieEntity> =
        dataMapper.listMap(response) { movie ->
            MovieEntity(
                id = movie.id,
                title = movie.name,
                subtitle = movie.description,
                isFavorite = false,
                type = HOME
            )
        }
}