package com.example.moviesapp.view.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.common.Result
import com.example.moviesapp.databinding.FragmentFavouriteBinding
import com.example.moviesapp.view.adapter.MovieAdapter
import com.example.moviesapp.view.adapter.MovieItem
import com.example.moviesapp.view.viewmodel.MovieViewModel
import com.example.moviesapp.view.viewmodel.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    @Inject
    lateinit var factory: MovieViewModelFactory
    private val favouriteViewModel by activityViewModels<MovieViewModel> { factory }
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater,container,false)
        setObserver()
        intialize()
        favouriteViewModel. getFavMovieList()
        return binding.root
    }

    private fun intialize() {
        val onClick: (MovieItem) -> Unit = { item ->
            favouriteViewModel.removeFromFavMovieList(item)
        }
        val onItemClick: (MovieItem) -> Unit = { item ->
            val action = FavouriteFragmentDirections.actionFavouriteToMovieDetail(item)
            findNavController().navigate(action)
        }
        adapter = MovieAdapter.newInstance(onClick, onItemClick)
        binding.rvFavourite.adapter = adapter
    }

    private fun setObserver() {
        favouriteViewModel.favMovieLiveData.observe(viewLifecycleOwner, Observer { response ->
            when(response) {

                is Result.Loading -> {
                    // handled in parent
                }

                is Result.Success -> {
                    if (response.data.toList().isEmpty()) {
                        binding.tvNoFavourite.visibility = View.VISIBLE
                    } else {
                        binding.tvNoFavourite.visibility = View.GONE
                        adapter.submitList(response.data.toList())
                    }
                }

                is Result.Error -> {
                    Toast.makeText(this.context, "Something went wrong. Please try again!", Toast.LENGTH_SHORT).show()
                    binding.tvNoFavourite.visibility = View.VISIBLE
                }
            }
        })
    }
}