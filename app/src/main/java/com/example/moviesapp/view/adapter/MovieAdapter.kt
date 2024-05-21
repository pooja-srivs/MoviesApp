package com.example.moviesapp.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesapp.view.adapter.viewholder.BaseViewHolder
import com.example.moviesapp.view.adapter.viewholder.factory.ViewHolderTypeFactory
import com.example.moviesapp.view.adapter.viewholder.factory.ViewHolderTypeFactoryImpl

class MovieAdapter private constructor(
    private val callback: DiffUtil.ItemCallback<MovieItem>,
    private val onFavClick: (MovieItem) -> Unit,
    private val onItemClick: (MovieItem) -> Unit
) : ListAdapter<MovieItem, BaseViewHolder<MovieItem>>(callback){

    private val viewHolderTypeFactory: ViewHolderTypeFactory = ViewHolderTypeFactoryImpl()

    companion object {
        private const val HOME = 0
        private const val FAVOURITE = 1
        private val callback = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem.isFav == newItem.isFav

        }
        fun newInstance(
            onFavClick: (MovieItem) -> Unit,
            onItemClick: (MovieItem) -> Unit
        ): MovieAdapter = MovieAdapter(callback, onFavClick, onItemClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MovieItem> {
        return viewHolderTypeFactory.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MovieItem>, position: Int) {
        holder.bind(getItem(position), onFavClick, onItemClick)
    }

    override fun getItemViewType(position: Int): Int {
       return if(getItem(position).type == 0) HOME else FAVOURITE
    }
}
