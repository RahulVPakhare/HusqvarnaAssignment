package com.husqvarna.assignment.test_data

import com.husqvarna.assignment.data.remote.model.Movie

class MovieTestData {

    companion object {

        private const val SIZE = 15

        fun getMovies(): Movie {
            val movieResults = arrayListOf<Movie.Result?>()
            for (i in 1..SIZE) {
                movieResults.add(
                    Movie.Result(
                        false,
                        "backdropPath$i",
                        arrayListOf(i, i + 1),
                        i,
                        "originalLanguage$i",
                        "originalTitle$i",
                        "overview$i",
                        i.toDouble(),
                        "posterPath$i",
                        "releaseDate$i",
                        "title$i",
                        false,
                        i.toDouble(),
                        i
                    )
                )
            }
            return Movie(0, movieResults, 1, SIZE)
        }

        fun getTenMostPopularMovies(): ArrayList<Movie.Result> {
            val movieResults = arrayListOf<Movie.Result>()
            for (i in SIZE downTo SIZE - 10 + 1) {
                movieResults.add(
                    Movie.Result(
                        false,
                        "backdropPath$i",
                        arrayListOf(i, i + 1),
                        i,
                        "originalLanguage$i",
                        "originalTitle$i",
                        "overview$i",
                        i.toDouble(),
                        "posterPath$i",
                        "releaseDate$i",
                        "title$i",
                        false,
                        i.toDouble(),
                        i
                    )
                )
            }
            return movieResults
        }
    }
}