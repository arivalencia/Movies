package com.ari.movies.ui.viewmodels

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ari.domain.model.*
import com.ari.domain.usecases.GetPlayingNowMoviesUseCase
import com.ari.domain.usecases.GetPopularMoviesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    private lateinit var moviesViewModel: MoviesViewModel

    @RelaxedMockK
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @RelaxedMockK
    private lateinit var getPlayingNowMoviesUseCase: GetPlayingNowMoviesUseCase

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        moviesViewModel = MoviesViewModel(getPopularMoviesUseCase, getPlayingNowMoviesUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when call to popular movies, set data to movies livedata`() = runTest {
        // Given
        val movie = MovieDomain(
            adult = false,
            backdropPath = "",
            genreIds = emptyList(),
            id = 1,
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity = 222.2,
            posterPath = "",
            releaseDate = "",
            title = "",
            video = false,
            voteAverage = 2.2,
            voteCount = 2.2
        )
        val movies = listOf(movie, movie, movie)
        val data = PopularMoviesResponseDomain(1, movies, 10, 10)
        coEvery { getPopularMoviesUseCase(1) } returns ResultDomain.Success(data)

        //When
        moviesViewModel.getPopularMovies()

        //Then
        assert(moviesViewModel.movies.value?.size == 3)
    }

    @Test
    fun `when call to playing now movies, set data to movies livedata`() = runTest {
        // Given
        val movie = MovieDomain(
            adult = false,
            backdropPath = "",
            genreIds = emptyList(),
            id = 1,
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity = 222.2,
            posterPath = "",
            releaseDate = "",
            title = "",
            video = false,
            voteAverage = 2.2,
            voteCount = 2.2
        )
        val movies = listOf(movie, movie, movie, movie)
        val data = PlayingNowResponseDomain(
            dates = DatesDomain("", ""),
            page = 10,
            totalPages = 10,
            totalResults = 5,
            results = movies
        )
        coEvery { getPlayingNowMoviesUseCase(1) } returns ResultDomain.Success(data)

        //When
        moviesViewModel.getPlayingNowMovies()

        //Then
        assert(moviesViewModel.movies.value?.size == 4)
    }

    @Test
    fun `when change to popular movies, reset page and movies`() = runTest {
        // Given

        //When
        moviesViewModel.changeToMostPopularMovies()

        //Then
        assert(moviesViewModel.movies.value!!.isEmpty())
        assert(moviesViewModel.currentPage.value!! == moviesViewModel.FIRST_PAGE)
    }

    @Test
    fun `when change to playing now movies, reset page and movies`() = runTest {
        // Given

        //When
        moviesViewModel.changeToPlayingNowMovies()

        //Then
        assert(moviesViewModel.movies.value!!.isEmpty())
        assert(moviesViewModel.currentPage.value!! == moviesViewModel.FIRST_PAGE)
    }

}