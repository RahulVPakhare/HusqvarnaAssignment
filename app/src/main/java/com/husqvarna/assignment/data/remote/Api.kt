package com.husqvarna.assignment.data.remote

import com.husqvarna.assignment.data.remote.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/3/movie/popular?")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Movie
}