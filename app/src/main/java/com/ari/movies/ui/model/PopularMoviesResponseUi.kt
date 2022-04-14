package com.ari.movies.ui.model

import com.ari.domain.model.PopularMoviesResponseDomain

data class PopularMoviesResponseUi(
    val page: Int,
    val results: List<MovieUi>,
    val total_pages: Int,
    val total_results: Int
)

fun PopularMoviesResponseDomain.toDomain(): PopularMoviesResponseUi = PopularMoviesResponseUi(
    page = page,
    results = results.map { it.toUi() },
    total_pages = total_pages,
    total_results = total_results
)