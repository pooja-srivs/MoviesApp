package com.example.moviesapp.view.mapper

import com.example.moviesapp.common.mapper.DataMapper
import com.example.moviesapp.data.db.MovieEntity
import com.example.moviesapp.view.adapter.MovieItem
import javax.inject.Inject

class ViewDataMapperImpl @Inject constructor(
    private val dataMapper: DataMapper
): ViewDataMapper {

    companion object {
        const val FAV = 1
    }

    override fun map(response: MovieItem): MovieEntity =
        dataMapper.map(response) { movie ->
            MovieEntity(
                id = movie.id,
                title = movie.title,
                subtitle = movie.subTitle,
                isFavorite = movie.isFav,
                type = movie.type
            )
        }

    override fun listMap(response: List<MovieItem>): List<MovieEntity> =
        dataMapper.listMap(response) { movie ->
            MovieEntity(
                id = movie.id,
                title = movie.title,
                subtitle = movie.subTitle,
                isFavorite = movie.isFav,
                type = FAV
            )
        }

    override fun listMapToMovieItem(response: List<MovieEntity>): List<MovieItem> =
        dataMapper.listMap(response) { movie ->
            MovieItem(
                id = movie.id,
                title = movie.title,
                subTitle = movie.subtitle,
                isFav = movie.isFavorite,
                type = FAV
            )
        }
}