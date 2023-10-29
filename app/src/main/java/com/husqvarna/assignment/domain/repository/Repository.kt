package com.husqvarna.assignment.domain.repository

import com.husqvarna.assignment.data.remote.model.Movie

interface Repository {
    suspend fun getPopularMovies(apiKey: String): Movie
}