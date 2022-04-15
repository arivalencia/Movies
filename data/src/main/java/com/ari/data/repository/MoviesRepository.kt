package com.ari.data.repository

import com.ari.data.contracts.MoviesLocalDataSource
import com.ari.data.contracts.MoviesRemoteDataSource
import com.ari.data.model.MovieData
import com.ari.data.model.PlayingNowResponseData
import com.ari.data.model.PopularMoviesResponseData
import com.ari.data.model.Response
import javax.inject.Inject
import javax.inject.Named

class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) {

    suspend fun getPopularMovies(page: Int): Response<PopularMoviesResponseData> =
        moviesRemoteDataSource.getPopularMovies(page)

    suspend fun getPlayingNowMovies(page: Int): Response<PlayingNowResponseData> =
        moviesRemoteDataSource.getPlayingNowMovies(page)

    suspend fun getPopularMoviesFromDB(page: Int): PopularMoviesResponseData? =
        moviesLocalDataSource.getPopularMoviesFromDB(page)

    suspend fun deletePopularMoviesFromDB(page: Int) =
        moviesLocalDataSource.deletePopularMoviesFromDB(page)

    suspend fun insertPopularMoviesToDB(data: PopularMoviesResponseData) =
        moviesLocalDataSource.insertPopularMoviesToDB(data)

    suspend fun getPlayingNowMoviesFromDB(page: Int): PlayingNowResponseData? =
        moviesLocalDataSource.getPlayingNowMoviesFromDB(page)

    suspend fun deletePlayingNowMoviesFromDB(page: Int) =
        moviesLocalDataSource.deletePlayingNowMoviesFromDB(page)

    suspend fun insertPlayingNowMoviesToDB(data: PlayingNowResponseData) =
        moviesLocalDataSource.insertPlayingNowMoviesToDB(data)

}