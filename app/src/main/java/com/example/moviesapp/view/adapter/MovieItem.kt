package com.example.moviesapp.view.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItem(
    val id: Int,
    val title: String,
    val subTitle: String,
    val isFav: Boolean,
    var type: Int
): Parcelable