package com.husqvarna.assignment.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.husqvarna.assignment.R
import com.husqvarna.assignment.common.Status
import com.husqvarna.assignment.data.remote.model.Movie
import com.husqvarna.assignment.databinding.ActivityMoviesListBinding
import com.husqvarna.assignment.presentation.detail.MovieDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListActivity : AppCompatActivity(), MovieClickListener {

    private val mViewModel by viewModels<MoviesListViewModel>()

    private lateinit var binding: ActivityMoviesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObserver()

        mViewModel.getPopularMovies()
    }

    private fun setObserver() {
        mViewModel.popularMovies.observe(this) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> setMovieData(resource.data)
                    Status.ERROR -> Toast.makeText(this, "", Toast.LENGTH_LONG).show()
                    Status.LOADING -> {}
                }
            }
        }
    }

    private fun setMovieData(data: Movie?) {
        findViewById<RecyclerView>(R.id.rvMovies).run {
            layoutManager = LinearLayoutManager(this@MoviesListActivity)
            setHasFixedSize(false)
            adapter =
                MoviesAdapter(mViewModel.getTenMostPopularMovies(data), this@MoviesListActivity)
        }
        binding.progressBar.visibility = View.GONE
    }

    override fun onMovieClick(movie: Movie.Result?) {
        startActivity(Intent(this, MovieDetailsActivity::class.java).apply {
            putExtra("movie", movie)
        })
    }
}