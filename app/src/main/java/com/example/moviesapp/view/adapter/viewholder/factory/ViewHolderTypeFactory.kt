package com.example.moviesapp.view.adapter.viewholder.factory

import android.view.ViewGroup
import com.example.moviesapp.view.adapter.MovieItem
import com.example.moviesapp.view.adapter.viewholder.BaseViewHolder

interface ViewHolderTypeFactory {

    fun create(parent: ViewGroup, viewType: Int): BaseViewHolder<MovieItem>
}