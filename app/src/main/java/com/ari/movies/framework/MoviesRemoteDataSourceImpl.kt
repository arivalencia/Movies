package com.ari.movies.framework

import com.ari.data.contracts.MoviesRemoteDataSource
import com.ari.data.model.PlayingNowResponseData
import com.ari.data.model.PopularMoviesResponseData
import com.ari.data.model.Response
import com.ari.movies.framework.data.remote.MoviesApi
import javax.inject.Inject
import javax.inject.Named

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    @Named("api_key") private val apiKey: String
): MoviesRemoteDataSource {

    // Get popular movies from api
    override suspend fun getPopularMovies(page: Int): Response<PopularMoviesResponseData> = try {
        val response = moviesApi.getPopularMovies(apiKey = apiKey, language = "", page = page.toString())
        if (response.isSuccessful) {
            Response.Success(response.body()!!)
        } else {
            Response.Error(response.message())
        }
    } catch (e: Exception) {
        Response.Error(e.toString())
    }

    // Get Playing Now Movies from api
    override suspend fun getPlayingNowMovies(page: Int): Response<PlayingNowResponseData> = try {
        val response = moviesApi.getPlayingNowMovies(apiKey = apiKey, language = "", page = page.toString())
        if (response.isSuccessful) {
            Response.Success(response.body()!!)
        } else {
            Response.Error(response.message())
        }
    } catch (e: Exception) {
        Response.Error(e.toString())
    }

}