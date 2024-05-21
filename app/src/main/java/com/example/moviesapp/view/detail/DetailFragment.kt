package com.example.moviesapp.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.moviesapp.databinding.FragmentDetailBinding
import com.example.moviesapp.view.adapter.MovieItem
import com.example.moviesapp.view.viewmodel.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val movieItem = args.movieItem
        setViewData(movieItem)
        return binding.root
    }

    private fun setViewData(movieItem: MovieItem) {
     binding.apply {
         titleTextView.text = movieItem.title
         subtitleTextView.text = movieItem.subTitle
         if (movieItem.isFav) likeButton.setColorFilter(android.graphics.Color.RED)
     }
    }
}