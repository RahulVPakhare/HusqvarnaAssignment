package com.husqvarna.assignment.domain.use_case

import com.husqvarna.assignment.common.Constant
import com.husqvarna.assignment.common.Resource
import com.husqvarna.assignment.data.remote.model.Movie
import com.husqvarna.assignment.di.AppModule
import com.husqvarna.assignment.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(@AppModule.Repository private val repository: Repository) {

    operator fun invoke(): Flow<Resource<Movie>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getPopularMovies(Constant.API_KEY)
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.toString(), null, 0))
        }
    }
}