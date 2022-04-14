package com.ari.data.contracts

import com.ari.data.model.PlayingNowResponseData
import com.ari.data.model.PopularMoviesResponseData

interface MoviesLocalDataSource {

    suspend fun getPopularMoviesFromDB(page: Int): PopularMoviesResponseData
    suspend fun deletePopularMoviesFromDB(page: Int)
    suspend fun insertPopularMoviesToDB(data: PopularMoviesResponseData)

    suspend fun getPlayingNowMoviesFromDB(page: Int): PlayingNowResponseData
    suspend fun deletePlayingNowMoviesFromDB(page: Int)
    suspend fun insertPlayingNowMoviesToDB(data: PlayingNowResponseData)

}