package com.husqvarna.assignment.domain.use_case

import com.husqvarna.assignment.common.Status
import com.husqvarna.assignment.data.repository.FakeRepository
import com.husqvarna.assignment.test_data.MovieTestData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseTest {

    // region constants

    // endregion constants

    // region helper fields
    private lateinit var fakeRepository: FakeRepository
    // endregion helper fields

    private lateinit var SUT: GetPopularMoviesUseCase

    @Before
    fun setup() {
        fakeRepository = FakeRepository()
        SUT = GetPopularMoviesUseCase(fakeRepository)
    }

    @Test
    fun getPopularMovies_loadingResponseReturned() = runBlocking {
        // Arrange
        // Act
        val result = SUT().toList().first()
        // Assert
        Assert.assertEquals(Status.LOADING, result.status)
    }

    @Test
    fun getPopularMovies_success_successResponseReturned() = runBlocking {
        // Arrange
        success()
        // Act
        val result = SUT().toList()[1]
        // Assert
        Assert.assertEquals(Status.SUCCESS, result.status)
    }

    @Test
    fun getPopularMovies_success_correctDataReturned() = runBlocking {
        // Arrange
        success()
        // Act
        val result = SUT().toList()[1]
        // Assert
        Assert.assertEquals(MovieTestData.getMovies(), result.data)
    }

    @Test
    fun getPopularMovies_failureResponse_errorReturned() = runBlocking {
        // Arrange
        failure()
        // Act
        val result = SUT().toList()[1]
        // Assert
        Assert.assertEquals(Status.ERROR, result.status)
    }

    // region helper methods
    private fun success() {
        // currently no-op
    }

    private fun failure() {
        fakeRepository.mFailure = true
    }
    // endregion helper methods

    // region helper classes

    // endregion helper classes
}