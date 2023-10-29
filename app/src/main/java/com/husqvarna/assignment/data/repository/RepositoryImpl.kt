package com.husqvarna.assignment.data.repository

import com.husqvarna.assignment.data.remote.Api
import com.husqvarna.assignment.data.remote.model.Movie
import com.husqvarna.assignment.di.AppModule
import com.husqvarna.assignment.domain.repository.Repository

class RepositoryImpl(@AppModule.Repository private val api: Api) : Repository {

    override suspend fun getPopularMovies(apiKey: String): Movie {
        return api.getPopularMovies(apiKey)
    }
}