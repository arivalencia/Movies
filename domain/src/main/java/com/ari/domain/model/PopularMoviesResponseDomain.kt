package com.ari.domain.model

import com.ari.data.model.PopularMoviesResponseData

data class PopularMoviesResponseDomain(
    val page: Int,
    val results: List<MovieDomain>,
    val total_pages: Int,
    val total_results: Int
)

fun PopularMoviesResponseData.toDomain(): PopularMoviesResponseDomain = PopularMoviesResponseDomain(
    page = page,
    results = results.map { it.toDomain() },
    total_pages = total_pages,
    total_results = total_results
)