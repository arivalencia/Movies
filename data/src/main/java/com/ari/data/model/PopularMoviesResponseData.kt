package com.ari.data.model

data class PopularMoviesResponseData(
    val page: Int,
    val results: List<MovieData>,
    val total_pages: Int,
    val total_results: Int
)