package com.husqvarna.assignment.data.repository

import com.husqvarna.assignment.data.remote.model.Movie
import com.husqvarna.assignment.domain.repository.Repository
import com.husqvarna.assignment.test_data.MovieTestData

class FakeRepository : Repository {

    var mFailure: Boolean = false

    override suspend fun getPopularMovies(apiKey: String): Movie {
        if (mFailure) {
            throw RuntimeException("Error while fetching movies")
        }
        return MovieTestData.getMovies()
    }
}