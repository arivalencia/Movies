package com.ari.movies.framework.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ari.data.model.PopularMoviesResponseData

@Entity(
    tableName = "popular_movies_page_table"
)
data class PopularMoviesPageEntity(
    @PrimaryKey
    val page: Int,

    val results: List<MovieEntity>,
    val total_pages: Int,
    val total_results: Int
)

fun PopularMoviesPageEntity.toData(): PopularMoviesResponseData = PopularMoviesResponseData(
    page = page, results = results.map { it.toData() }, total_pages = total_pages, total_results = total_results
)

fun PopularMoviesResponseData.toEntity(): PopularMoviesPageEntity = PopularMoviesPageEntity(
    page = page, results = results.map { it.toEntity() }, total_pages = total_pages, total_results = total_results
)
