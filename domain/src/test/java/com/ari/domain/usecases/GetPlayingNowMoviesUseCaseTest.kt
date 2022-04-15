package com.ari.domain.usecases

import com.ari.data.model.DatesData
import com.ari.data.model.PlayingNowResponseData
import com.ari.data.model.Response
import com.ari.data.repository.MoviesRepository
import com.ari.domain.model.PlayingNowResponseDomain
import com.ari.domain.model.ResultDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPlayingNowMoviesUseCaseTest {

    private lateinit var getPlayingNowMoviesUseCase: GetPlayingNowMoviesUseCase

    @RelaxedMockK
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPlayingNowMoviesUseCase = GetPlayingNowMoviesUseCase(moviesRepository)
    }

    @Test
    fun `when the api return an error then get movies from local database`() = runBlocking {
        // Given
        coEvery { moviesRepository.getPlayingNowMovies(1) } returns Response.Error("error")

        // When
        val result: ResultDomain<PlayingNowResponseDomain> = getPlayingNowMoviesUseCase(1)

        // Then
        coVerify(exactly = 1) { moviesRepository.getPlayingNowMoviesFromDB(any()) }
        coVerify(exactly = 0) { moviesRepository.deletePlayingNowMoviesFromDB(any()) }
        coVerify(exactly = 0) { moviesRepository.insertPlayingNowMoviesToDB(any()) }
    }

    @Test
    fun `when the api return success data then save in local database onf get it`() = runBlocking {
        // Given
        val obj = PlayingNowResponseData(
            DatesData("", ""),
            1,
            10,
            10,
            emptyList()
        )
        coEvery { moviesRepository.getPlayingNowMovies(1) } returns Response.Success(obj)

        // When
        val result: ResultDomain<PlayingNowResponseDomain> = getPlayingNowMoviesUseCase(1)

        // Then
        coVerify(exactly = 0) { moviesRepository.getPlayingNowMoviesFromDB(any()) }
        coVerify(exactly = 1) { moviesRepository.deletePlayingNowMoviesFromDB(any()) }
        coVerify(exactly = 1) { moviesRepository.insertPlayingNowMoviesToDB(any()) }
    }

    @Test
    fun `when page is less than 1 then get invalid page error`() = runBlocking {
        // Given

        // When
        val result: ResultDomain<PlayingNowResponseDomain> = getPlayingNowMoviesUseCase(0)

        // Then
        coVerify(exactly = 0) { moviesRepository.getPlayingNowMoviesFromDB(any()) }
        coVerify(exactly = 0) { moviesRepository.deletePlayingNowMoviesFromDB(any()) }
        coVerify(exactly = 0) { moviesRepository.insertPlayingNowMoviesToDB(any()) }
        assert(result is ResultDomain.Error)
    }

}