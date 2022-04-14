package com.ari.movies.framework.data.remote

import com.ari.data.model.PlayingNowResponseData
import com.ari.data.model.PopularMoviesResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: String,
    ): Response<PopularMoviesResponseData>

    @GET("movie/now_playing")
    suspend fun getPlayingNowMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: String,
    ): Response<PlayingNowResponseData>

}