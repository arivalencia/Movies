package com.ari.data.contracts

import com.ari.data.model.PlayingNowResponseData
import com.ari.data.model.PopularMoviesResponseData
import com.ari.data.model.Response

interface MoviesRemoteDataSource {

    suspend fun getPopularMovies(page: Int): Response<PopularMoviesResponseData>

    suspend fun getPlayingNowMovies(page: Int): Response<PlayingNowResponseData>

}