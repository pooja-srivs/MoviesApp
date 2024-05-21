package com.example.moviesapp.view.home

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
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.view.adapter.MovieAdapter
import com.example.moviesapp.view.adapter.MovieItem
import com.example.moviesapp.view.viewmodel.MovieViewModel
import com.example.moviesapp.view.viewmodel.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var factory: MovieViewModelFactory
    private val homeViewModel by activityViewModels<MovieViewModel> { factory }
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container,false)
        setUpView()
        setObserver()
        homeViewModel.getMovieData()
        return binding.root
    }

    private fun setUpView() {
        val onFavClick: (MovieItem) -> Unit = { item ->
            homeViewModel.setFavMovie(item)
        }

        val onItemClick: (MovieItem) -> Unit = { item ->
            val action = HomeFragmentDirections.actionHomeFragmentToMovieDetail(item)
            findNavController().navigate(action)
        }

        adapter = MovieAdapter.newInstance(onFavClick, onItemClick)
        binding.rvHome.adapter = adapter
    }

    private fun setObserver() {
        homeViewModel.homeLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {

                is Result.Loading -> {
                    // handled in parent
                }

                is Result.Success -> {
                    adapter.submitList(response.data)
                }

                is Result.Error -> {
                    Toast.makeText(this.context, "Something went wrong. Please try again!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}