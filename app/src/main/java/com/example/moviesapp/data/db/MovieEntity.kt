package com.example.moviesapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity (
    @PrimaryKey val id: Int,
    val title: String,
    val subtitle: String,
    val isFavorite: Boolean,
    val type: Int
)