package com.ari.domain.usecases

import com.ari.data.model.PopularMoviesResponseData
import com.ari.data.model.Response
import com.ari.data.repository.MoviesRepository
import com.ari.domain.model.PopularMoviesResponseDomain
import com.ari.domain.model.ResultDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class GetPopularMoviesUseCaseTest {

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @RelaxedMockK
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPopularMoviesUseCase = GetPopularMoviesUseCase(moviesRepository)
    }

    @Test
    fun `when the api return an error then get movies from local database`() = runBlocking {
        // Given
        coEvery { moviesRepository.getPopularMovies(1) } returns Response.Error("error")

        // When
        val result: ResultDomain<PopularMoviesResponseDomain> = getPopularMoviesUseCase(1)

        // Then
        coVerify(exactly = 1) { moviesRepository.getPopularMoviesFromDB(any()) }
        coVerify(exactly = 0) { moviesRepository.deletePopularMoviesFromDB(any()) }
        coVerify(exactly = 0) { moviesRepository.insertPopularMoviesToDB(any()) }
    }

    @Test
    fun `when the api return success data then save in local database onf get it`() = runBlocking {
        // Given
        val obj = PopularMoviesResponseData(
            1,
            emptyList(),
            10,
            10
        )
        coEvery { moviesRepository.getPopularMovies(1) } returns Response.Success(obj)

        // When
        val result: ResultDomain<PopularMoviesResponseDomain> = getPopularMoviesUseCase(1)

        // Then
        coVerify(exactly = 0) { moviesRepository.getPopularMoviesFromDB(any()) }
        coVerify(exactly = 1) { moviesRepository.deletePopularMoviesFromDB(any()) }
        coVerify(exactly = 1) { moviesRepository.insertPopularMoviesToDB(any()) }
        assert(result is ResultDomain.Success)
    }

    @Test
    fun `when page is less than 1 then get invalid page error`() = runBlocking {
        // Given

        // When
        val result: ResultDomain<PopularMoviesResponseDomain> = getPopularMoviesUseCase(0)

        // Then
        coVerify(exactly = 0) { moviesRepository.getPlayingNowMoviesFromDB(any()) }
        coVerify(exactly = 0) { moviesRepository.deletePlayingNowMoviesFromDB(any()) }
        coVerify(exactly = 0) { moviesRepository.insertPlayingNowMoviesToDB(any()) }
        assert(result is ResultDomain.Error)
    }

}