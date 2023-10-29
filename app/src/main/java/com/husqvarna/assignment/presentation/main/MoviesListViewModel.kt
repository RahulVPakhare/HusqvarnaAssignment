package com.husqvarna.assignment.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.husqvarna.assignment.common.Resource
import com.husqvarna.assignment.data.remote.model.Movie
import com.husqvarna.assignment.domain.use_case.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) :
    ViewModel() {

    private val _popularMovies: MutableLiveData<Resource<Movie>> = MutableLiveData()
    val popularMovies: LiveData<Resource<Movie>>
        get() = _popularMovies

    fun getPopularMovies() = viewModelScope.launch(Dispatchers.IO) {
        getPopularMoviesUseCase().collect {
            _popularMovies.postValue(it)
        }
    }

    fun getTenMostPopularMovies(data: Movie?): ArrayList<Movie.Result?>? =
        data?.results?.sortedByDescending {
            it?.popularity
        }?.take(10) as ArrayList<Movie.Result?>?
}