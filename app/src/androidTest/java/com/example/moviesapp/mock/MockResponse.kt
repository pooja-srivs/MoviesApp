package com.example.moviesapp.mock

import com.example.moviesapp.data.db.MovieEntity

object MockResponse {

    val expected =  listOf(
        MovieEntity(
            id = 1,
            title = "Movie",
            subtitle = "Summary",
            isFavorite = true,
            type = 0
        )
    )

    val current = listOf(
        MovieEntity(
            id = 1,
            title = "Movie",
            subtitle = "Summary",
            isFavorite = true,
            type = 0
        ),
        MovieEntity(
            id = 2,
            title = "Movie",
            subtitle = "Summary",
            isFavorite = false,
            type = 0
        ),
        MovieEntity(
            id = 3,
            title = "Movie",
            subtitle = "Summary",
            isFavorite = true,
            type = 0
        )
    )
}