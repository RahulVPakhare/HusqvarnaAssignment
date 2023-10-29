package com.husqvarna.assignment.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.husqvarna.assignment.common.CoroutineRule
import com.husqvarna.assignment.common.Resource
import com.husqvarna.assignment.common.Status
import com.husqvarna.assignment.common.getOrAwaitValue
import com.husqvarna.assignment.domain.use_case.GetPopularMoviesUseCase
import com.husqvarna.assignment.test_data.MovieTestData
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MoviesListViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // region constants
    private val MOVIE = MovieTestData.getMovies()
    private val TEN_MOST_POPULAR_MOVIES = MovieTestData.getTenMostPopularMovies()
    private val ERROR_MESSAGE = "Error fetching movies"
    private val ERROR_CODE = 1
    // endregion constants

    // region helper fields
    @Mock
    private lateinit var getPopularMoviesUseCaseMock: GetPopularMoviesUseCase
    // endregion helper fields

    lateinit var SUT: MoviesListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        SUT = MoviesListViewModel(getPopularMoviesUseCaseMock)
    }

    @Test
    fun getPopularMovies_loadingStatusReturned() {
        // Arrange
        loading()
        // Act
        SUT.getPopularMovies()
        // Assert
        val result = SUT.popularMovies.getOrAwaitValue()
        Assert.assertEquals(Status.LOADING, result.status)
    }

    @Test
    fun getPopularMovies_success_successStatusReturned() {
        // Arrange
        success()
        // Act
        SUT.getPopularMovies()
        // Assert
        val result = SUT.popularMovies.getOrAwaitValue()
        Assert.assertEquals(Status.SUCCESS, result.status)
    }

    @Test
    fun getPopularMovies_success_correctDataReturned() {
        // Arrange
        success()
        // Act
        SUT.getPopularMovies()
        // Assert
        val result = SUT.popularMovies.getOrAwaitValue()
        Assert.assertEquals(MOVIE, result.data)
    }

    @Test
    fun getPopularMovies_failure_errorStatusReturned() {
        // Arrange
        failure()
        // Act
        SUT.getPopularMovies()
        // Assert
        val result = SUT.popularMovies.getOrAwaitValue()
        Assert.assertEquals(Status.ERROR, result.status)
    }

    @Test
    fun getPopularMovies_failure_nullDataReturned() {
        // Arrange
        failure()
        // Act
        SUT.getPopularMovies()
        // Assert
        val result = SUT.popularMovies.getOrAwaitValue()
        Assert.assertNull(result.data)
    }

    @Test
    fun getTenMostPopularMovies_tenMostPopularMoviesReturned() {
        // Arrange
        success()
        // Act
        val result = SUT.getTenMostPopularMovies(MOVIE)
        // Assert
        Assert.assertEquals(TEN_MOST_POPULAR_MOVIES, result)
    }

    // region helper methods
    private fun loading() {
        Mockito.`when`(getPopularMoviesUseCaseMock.invoke())
            .thenReturn(flow { emit(Resource.Loading()) })
    }

    private fun success() {
        Mockito.`when`(getPopularMoviesUseCaseMock.invoke())
            .thenReturn(flow { emit(Resource.Success(MOVIE)) })
    }

    private fun failure() {
        Mockito.`when`(getPopularMoviesUseCaseMock.invoke())
            .thenReturn(flow { emit(Resource.Error(ERROR_MESSAGE, null, ERROR_CODE)) })
    }
    // endregion helper methods

    // region helper classes

    // endregion helper classes
}