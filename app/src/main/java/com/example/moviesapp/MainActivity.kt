package com.example.moviesapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.moviesapp.common.Result
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.view.viewmodel.MovieViewModel
import com.example.moviesapp.view.viewmodel.MovieViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MovieViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigation()
        setObserver()
    }

    private fun setNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_detail) {
                binding.navView.visibility = View.GONE
            } else {
                binding.navView.visibility = View.VISIBLE
            }
        }
    }

    private fun setObserver() {
        viewModel.homeLiveData.observe(this, Observer { result ->
            when (result) {
                is Result.Success -> {
                    binding.lottieLoader.visibility = View.GONE
                }
                is Result.Error -> {
                    binding.lottieLoader.visibility = View.GONE
                }
                is Result.Loading -> {
                    binding.lottieLoader.visibility = View.VISIBLE
                }
            }
        })
    }
}