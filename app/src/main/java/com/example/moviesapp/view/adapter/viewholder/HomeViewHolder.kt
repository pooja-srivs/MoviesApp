package com.example.moviesapp.view.adapter.viewholder

import com.example.moviesapp.databinding.ItemBinding
import com.example.moviesapp.view.adapter.MovieItem

class HomeViewHolder(private val binding: ItemBinding) : BaseViewHolder<MovieItem>(binding.root) {

    override fun bind(data: MovieItem, onClick: (MovieItem) -> Unit, onItemClick: (MovieItem) -> Unit) {
        with(binding) {
            titleTextView.text = data.title
            subtitleTextView.text = data.subTitle
            if (data.isFav) likeButton.setColorFilter(android.graphics.Color.RED)
            likeButton.setOnClickListener {
                likeButton.setColorFilter(android.graphics.Color.RED)
                onClick(data)
            }
            root.setOnClickListener {
                onItemClick(data)
            }
        }
    }
}