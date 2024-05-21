package com.example.moviesapp.view.adapter.viewholder.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ItemBinding
import com.example.moviesapp.view.adapter.MovieItem
import com.example.moviesapp.view.adapter.viewholder.BaseViewHolder
import com.example.moviesapp.view.adapter.viewholder.FavouriteViewHolder
import com.example.moviesapp.view.adapter.viewholder.HomeViewHolder
import javax.inject.Inject

class ViewHolderTypeFactoryImpl @Inject constructor(): ViewHolderTypeFactory {

    companion object {
        private const val HOME = 0
        private const val FAVOURITE = 1

    }
    override fun create(parent: ViewGroup, viewType: Int): BaseViewHolder<MovieItem> {
        return when(viewType) {

            HOME -> HomeViewHolder(
                ItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            FAVOURITE -> FavouriteViewHolder(
                ItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> HomeViewHolder(
                ItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

}