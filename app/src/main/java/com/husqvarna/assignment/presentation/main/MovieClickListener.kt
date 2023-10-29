package com.husqvarna.assignment.presentation.main

import com.husqvarna.assignment.data.remote.model.Movie

interface MovieClickListener {
    fun onMovieClick(movie: Movie.Result?)
}