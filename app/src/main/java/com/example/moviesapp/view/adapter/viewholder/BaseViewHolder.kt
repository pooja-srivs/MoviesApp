package com.example.moviesapp.view.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.view.adapter.MovieItem

abstract class BaseViewHolder<T>(view: View): RecyclerView.ViewHolder(view) {

    abstract fun bind(data: T, onClick: (MovieItem) -> Unit, onItemClick: (MovieItem) -> Unit)
}