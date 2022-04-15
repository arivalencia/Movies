package com.ari.domain.model

import com.ari.data.model.MovieData

data class MovieDomain(
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Double
)

fun MovieData.toDomain(): MovieDomain = MovieDomain(
    adult = adult,
    backdropPath = backdrop_path,
    genreIds = genre_ids,
    id = id,
    originalLanguage = original_language,
    originalTitle = original_title,
    overview = overview,
    popularity = popularity,
    posterPath = poster_path,
    releaseDate = release_date,
    title = title,
    video = video,
    voteAverage = vote_average,
    voteCount = vote_count
)